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

    void "Ensure DB Empty at start"() {
      expect:
        RefdataValue.count() == 0;
    }

    void "Test global row status creation"() {
      when:
        RefdataValue grs_a = RefdataValue.lookupOrCreateGlobal('GLOBAL_ROW_STATUS','ACTIVE','Active');
        RefdataValue grs_d = RefdataValue.lookupOrCreateGlobal('GLOBAL_ROW_STATUS','DELETED','Deleted');
      then:
        println("Test global row status creation");
        RefdataValue.list().each {
          println("rdv:${it}");
        }
        RefdataValue.count() == 2;
        RefdataValue.executeQuery('select count(rdv.id) from RefdataValue as rdv where rdv.category.shortcode=:o',['o':'GLOBAL_ROW_STATUS']).get(0) == 2
    }

    void "simpleTest"() {
      when:
        RefdataValue grs_a = RefdataValue.lookupOrCreateGlobal('c1','v1','v11');
        RefdataValue grs_1 = RefdataValue.lookupOrCreateGlobal('c2','v2','v22');
      then:
        RefdataValue.list().each { println("rdv:${it}"); }
        RefdataValue.count() == 2;
        RefdataValue.executeQuery('select count(rdv.id) from RefdataValue as rdv where rdv.category.shortcode=:c',['c':'c1']).get(0) == 1
        RefdataValue.executeQuery('select count(rdv.id) from RefdataValue as rdv where rdv.category.shortcode=:c',['c':'c2']).get(0) == 1
    }

    void "\n\nTest Refdata Creation"() {
      when:
        println("\nCreate test values");
        RefdataValue grs_a = RefdataValue.lookupOrCreateGlobal('GLOBAL_ROW_STATUS','ACTIVE','Active');
        RefdataValue grs_d = RefdataValue.lookupOrCreateGlobal('GLOBAL_ROW_STATUS','DELETED','Deleted');
        println("\n\n Created globls, create TESTCAT-1 first value--");
        RefdataValue.list().each { println("rdv:${it}"); }
        RefdataValue c1v1 = RefdataValue.lookupOrCreateGlobal('TESTCAT-1','TESTVAL-1','Test Value 1');
        println("\n\n--");
        RefdataValue.list().each { println("rdv:${it}"); }
        RefdataValue c1v2 = RefdataValue.lookupOrCreateGlobal('TESTCAT-1','TESTVAL-2','Test Value 2');
        RefdataValue c1v3 = RefdataValue.lookupOrCreateGlobal('TESTCAT-1','TESTVAL-3','Test Value 3');
      then:
        println("-- Test Refdata Creation --");
        RefdataValue.list().each { println("rdv:${it}"); }
        // 5 values altogether
        RefdataValue.count() == 5;
        // 3 in TESTCAT-1
        RefdataValue.executeQuery('select count(rdv.id) from RefdataValue as rdv where rdv.category.shortcode=:tc1',['tc1':'TESTCAT-1']).get(0) == 3
        // 1 in TESTCAT-1 with a shortcode of TESTVAL-1
        RefdataValue.executeQuery('select count(rdv.id) from RefdataValue as rdv where rdv.category.shortcode=:tc1 and rdv.shortcode=:s',['tc1':'TESTCAT-1', 's':'TESTVAL-1']).get(0) == 1
    }
}
