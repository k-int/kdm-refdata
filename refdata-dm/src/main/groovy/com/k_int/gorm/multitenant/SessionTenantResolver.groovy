package com.k_int.gorm.multitenant


import groovy.transform.CompileStatic
import org.grails.datastore.mapping.core.connections.ConnectionSource
import org.grails.datastore.mapping.multitenancy.TenantResolver
import org.grails.datastore.mapping.multitenancy.exceptions.TenantNotFoundException
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletWebRequest
import grails.gorm.DetachedCriteria

import org.grails.datastore.mapping.multitenancy.TenantResolver;
import org.grails.datastore.mapping.multitenancy.AllTenantsResolver;

import com.k_int.dm.tenant.Tenant

/**
 * Largely based on https://github.com/grails/grails-data-mapping/blob/master/grails-datastore-web/src/main/groovy/org/grails/datastore/mapping/multitenancy/web/SessionTenantResolver.groovy
 */
class SessionTenantResolver implements TenantResolver, AllTenantsResolver {

  public SessionTenantResolver() {
  }

  @Override
  Serializable resolveTenantIdentifier() throws TenantNotFoundException {
    // RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()

    // if(requestAttributes != null) {
    //   def tenantId = requestAttributes.getAttribute(attributeName, RequestAttributes.SCOPE_SESSION)
    //   if(tenantId instanceof Serializable) {
    //     return (Serializable)tenantId
    //   }
    //   else {
    //     throw new TenantNotFoundException()
    //   }
    // }
    // throw new TenantNotFoundException("Tenant could not be resolved outside a web request")
    return (Serializable)'knowint_test_tenant';
  }

  /**
   * http://gorm.grails.org/latest/hibernate/manual/index.html#multiTenancy suggests detached criteria as follows::
   */
  @Override
  java.lang.Iterable<java.io.Serializable> resolveTenantIds() {
    // println("\n\n**\n**\nSessionTenantResolver::resolveTenantIds\n**\n");
    // new DetachedCriteria(Tenant).distinct('name').list()
    return [ 'knowint_test_tenant' ]
  }
}
