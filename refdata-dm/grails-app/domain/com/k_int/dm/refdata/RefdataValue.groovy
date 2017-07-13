package com.k_int.dm.refdata

class RefdataValue {

  /**
   * Shortcode for this value. Shortcodes SHOULD be constrained to 'A..Za..z0..9' and '_' or '-' shortcodes MUST NOT contain contain ' ', ':', '.' or any quote marks
   * Examples of good shortcodes: PENDING, IN_PROCESS, CHECKED_IN, CheckedIn, Checked-In
   * Examples of bad shortcodes: "CHECKED IN" "SOME.VALUE" "SOME:VALUE"
   * 
   * The java convention is for shortcodes to be camel case, ISO protocols tend to use uppercase and underscore. Within the constraints above,
   * it's probably best to use the format in the controlled list if it has one, otherwise fall back to camel case.
   */
  String shortcode

  /**
   * Owning category that this value belongs to
   */
  RefdataCategory category

  /**
   * Default label in the absence of i8n values
   */
  String defaultLabel

  /**
   * Control the sort order of options.
   */
  String sortKey

  /**
   * Default style for display - used to add an icon for example in UI
   */
  String defaultStyle

  /**
   *  RefdataValue status should be constrained to values from the GLOBAL_ROW_STATUS category - null, ACTIVE or DELETED
   */
  RefdataValue status

  static constraints = {
    category nullable:false, blank: false
    shortcode nullable:false, blank: false
    defaultLabel nullable:false, blank: false
    sortKey nullable:true, blank: false
    defaultStyle nullable:true, blank: false
    status nullable:true, blank: false
  }

  static mapping = {
    table 'ki_refdata_value'
    id column:'krv_id'
    category column:'krv_krc_id'
    shortcode column:'krv_shortcode'
    defaultLabel column:'krv_default_label'
    sortKey column:'krv_sort_key'
    defaultStyle column:'krv_default_style'
    status column:'krv_status'
  }

  public static RefdataValue lookupOrCreateGlobal(String category_shortcode, String value_shortcode, String default_label) {
    // Lookup or create the category
    def cat = RefdataCategory.lookupOrCreateGlobal(category_shortcode);

    // Lookup or create the value, groovy will automatically retrun the result of the last evaluated statement in a function
    RefdataValue result = RefdataValue.findByCategoryAndShortcode(cat,value_shortcode) 

    if ( result == null ) {
      result = new RefdataValue()
      result.category = cat
      result.shortcode=value_shortcode
      result.defaultLabel=default_label
      result.save(flush:true, failOnError:true)
    }

    println("Returning ${result}");
    return result
  }

  public static RefdataValue resolve(String status_str) {

    String[] status_components = status_str.split(':');
    RefdataValue result = null;

    if ( status_components.length == 2 ) {
      def vl = RefdataValue.executeQuery('select rdv from RefdataValue as rdv where rdv.category.shortcode = :os and rdv.shortcode = :s',
                                         [os:status_components[0],s:status_components[1]]);
      if ( vl.size() == 1 ) {
        result=vl.get(0);
      }
    }
    else {
      throw new RuntimeException("resolve requires a colon separated string formatted as CATEGORY_SHORTCODE:VALUE_SHORTCODE but received \"${status_str}\"");
    }

    return result
  }

  public String toString() {
    "RefdataValue(${id},${category.id}:${category.shortcode},${shortcode},${defaultLabel},${sortKey})"
  }
}
