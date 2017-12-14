package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CiudadServiceSpec extends Specification {

    CiudadService ciudadService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Ciudad(...).save(flush: true, failOnError: true)
        //new Ciudad(...).save(flush: true, failOnError: true)
        //Ciudad ciudad = new Ciudad(...).save(flush: true, failOnError: true)
        //new Ciudad(...).save(flush: true, failOnError: true)
        //new Ciudad(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //ciudad.id
    }

    void "test get"() {
        setupData()

        expect:
        ciudadService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Ciudad> ciudadList = ciudadService.list(max: 2, offset: 2)

        then:
        ciudadList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        ciudadService.count() == 5
    }

    void "test delete"() {
        Long ciudadId = setupData()

        expect:
        ciudadService.count() == 5

        when:
        ciudadService.delete(ciudadId)
        sessionFactory.currentSession.flush()

        then:
        ciudadService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Ciudad ciudad = new Ciudad()
        ciudadService.save(ciudad)

        then:
        ciudad.id != null
    }
}
