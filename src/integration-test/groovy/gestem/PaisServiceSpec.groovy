package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PaisServiceSpec extends Specification {

    PaisService paisService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Pais(...).save(flush: true, failOnError: true)
        //new Pais(...).save(flush: true, failOnError: true)
        //Pais pais = new Pais(...).save(flush: true, failOnError: true)
        //new Pais(...).save(flush: true, failOnError: true)
        //new Pais(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pais.id
    }

    void "test get"() {
        setupData()

        expect:
        paisService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Pais> paisList = paisService.list(max: 2, offset: 2)

        then:
        paisList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        paisService.count() == 5
    }

    void "test delete"() {
        Long paisId = setupData()

        expect:
        paisService.count() == 5

        when:
        paisService.delete(paisId)
        sessionFactory.currentSession.flush()

        then:
        paisService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Pais pais = new Pais()
        paisService.save(pais)

        then:
        pais.id != null
    }
}
