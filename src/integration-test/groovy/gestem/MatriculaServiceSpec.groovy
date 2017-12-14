package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MatriculaServiceSpec extends Specification {

    MatriculaService matriculaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Matricula(...).save(flush: true, failOnError: true)
        //new Matricula(...).save(flush: true, failOnError: true)
        //Matricula matricula = new Matricula(...).save(flush: true, failOnError: true)
        //new Matricula(...).save(flush: true, failOnError: true)
        //new Matricula(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //matricula.id
    }

    void "test get"() {
        setupData()

        expect:
        matriculaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Matricula> matriculaList = matriculaService.list(max: 2, offset: 2)

        then:
        matriculaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        matriculaService.count() == 5
    }

    void "test delete"() {
        Long matriculaId = setupData()

        expect:
        matriculaService.count() == 5

        when:
        matriculaService.delete(matriculaId)
        sessionFactory.currentSession.flush()

        then:
        matriculaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Matricula matricula = new Matricula()
        matriculaService.save(matricula)

        then:
        matricula.id != null
    }
}
