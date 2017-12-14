package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TelefonoServiceSpec extends Specification {

    TelefonoService telefonoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Telefono(...).save(flush: true, failOnError: true)
        //new Telefono(...).save(flush: true, failOnError: true)
        //Telefono telefono = new Telefono(...).save(flush: true, failOnError: true)
        //new Telefono(...).save(flush: true, failOnError: true)
        //new Telefono(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //telefono.id
    }

    void "test get"() {
        setupData()

        expect:
        telefonoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Telefono> telefonoList = telefonoService.list(max: 2, offset: 2)

        then:
        telefonoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        telefonoService.count() == 5
    }

    void "test delete"() {
        Long telefonoId = setupData()

        expect:
        telefonoService.count() == 5

        when:
        telefonoService.delete(telefonoId)
        sessionFactory.currentSession.flush()

        then:
        telefonoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Telefono telefono = new Telefono()
        telefonoService.save(telefono)

        then:
        telefono.id != null
    }
}
