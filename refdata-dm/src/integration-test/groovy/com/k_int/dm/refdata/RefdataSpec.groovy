package com.k_int.dm.refdata


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*
import com.k_int.dm.refdata.*

@Integration
@Rollback
class RefdataSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test Refdata Creation"() {
      when:
        RefdataValue c1v1 = RefdataValue.lookupOrCreateGlobal('TESTCAT-1','TESTVAL-1','Test Value 1');
        RefdataValue c1v2 = RefdataValue.lookupOrCreateGlobal('TESTCAT-2','TESTVAL-2','Test Value 2');
        RefdataValue c1v3 = RefdataValue.lookupOrCreateGlobal('TESTCAT-3','TESTVAL-3','Test Value 3');
      then:
        RefdataValue.count() == 3;
    }
}
