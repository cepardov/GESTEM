package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CorreoServiceSpec extends Specification {

    CorreoService correoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Correo(...).save(flush: true, failOnError: true)
        //new Correo(...).save(flush: true, failOnError: true)
        //Correo correo = new Correo(...).save(flush: true, failOnError: true)
        //new Correo(...).save(flush: true, failOnError: true)
        //new Correo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //correo.id
    }

    void "test get"() {
        setupData()

        expect:
        correoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Correo> correoList = correoService.list(max: 2, offset: 2)

        then:
        correoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        correoService.count() == 5
    }

    void "test delete"() {
        Long correoId = setupData()

        expect:
        correoService.count() == 5

        when:
        correoService.delete(correoId)
        sessionFactory.currentSession.flush()

        then:
        correoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Correo correo = new Correo()
        correoService.save(correo)

        then:
        correo.id != null
    }
}
