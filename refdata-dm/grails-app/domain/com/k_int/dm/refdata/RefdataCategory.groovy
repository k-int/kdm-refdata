package com.k_int.dm.refdata

import com.k_int.dm.tenant.Tenant

/**
 * A RefdataCategory category is a controlled list, for example "ISO_10161_STATE".
 * A category manages a number of RefdataValues, for example "IDLE","IN-PROCESS","CHECKED-IN".
 * A global category has no owner.
 * A tenant may override and extend a global category by creating a new category with the same shortcode as the global list
 * but setting the owner to it's own TenantId. Values may then be added or overridden.
 * Values are *Never* deleted from a list, but may be set to "deleted" status.
 */
class RefdataCategory {

  Tenant owner
  String shortcode
  RefdataValue categoryStatus

  static constraints = {
    tenant nullable:true, blank: false
    shortcode nullable:false, blank: false
    categoryStatus nullable:true, blank: false
  }

  static mapping = {
    table 'ki_refdata_cat'
    id column:'krc_id'
    owner column:'krc_owner'
    shortcode column:'krc_shortcode'
    categoryStatus column:'krc_status'
  }

}
