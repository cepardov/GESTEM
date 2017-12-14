package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EnseñanzaServiceSpec extends Specification {

    EnseñanzaService enseñanzaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Enseñanza(...).save(flush: true, failOnError: true)
        //new Enseñanza(...).save(flush: true, failOnError: true)
        //Enseñanza enseñanza = new Enseñanza(...).save(flush: true, failOnError: true)
        //new Enseñanza(...).save(flush: true, failOnError: true)
        //new Enseñanza(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //enseñanza.id
    }

    void "test get"() {
        setupData()

        expect:
        enseñanzaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Enseñanza> enseñanzaList = enseñanzaService.list(max: 2, offset: 2)

        then:
        enseñanzaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        enseñanzaService.count() == 5
    }

    void "test delete"() {
        Long enseñanzaId = setupData()

        expect:
        enseñanzaService.count() == 5

        when:
        enseñanzaService.delete(enseñanzaId)
        sessionFactory.currentSession.flush()

        then:
        enseñanzaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Enseñanza enseñanza = new Enseñanza()
        enseñanzaService.save(enseñanza)

        then:
        enseñanza.id != null
    }
}
