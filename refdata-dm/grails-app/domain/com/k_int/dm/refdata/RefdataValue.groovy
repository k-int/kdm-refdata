package com.k_int.dm.refdata

class RefdataValue {

  /**
   * Owning category that this value belongs to
   */
  RefdataCategory owner

  /**
   * Shortcode for this value. 
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
