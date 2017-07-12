package com.k_int.dm.refdata

class RefdataValue {

  /**
   * Owning category that this value belongs to
   */
  RefdataCategory owner

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
    owner nullable:false, blank: false
    shortcode nullable:false, blank: false
    defaultLabel nullable:false, blank: false
    sortKey nullable:true, blank: false
    defaultStyle nullable:true, blank: false
    status nullable:true, blank: false
  }

  static mapping = {
    table 'ki_refdata_value'
    id column:'krv_id'
    owner column:'krv_krc_id'
    shortcode column:'krv_shortcode'
    defaultLabel column:'krv_default_label'
    sortKey column:'krv_sort_key'
    defaultStyle column:'krv_default_style'
    status column:'krv_status'
  }

}
