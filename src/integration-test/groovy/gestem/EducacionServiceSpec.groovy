package gestem

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EducacionServiceSpec extends Specification {

    EducacionService educacionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Educacion(...).save(flush: true, failOnError: true)
        //new Educacion(...).save(flush: true, failOnError: true)
        //Educacion educacion = new Educacion(...).save(flush: true, failOnError: true)
        //new Educacion(...).save(flush: true, failOnError: true)
        //new Educacion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //educacion.id
    }

    void "test get"() {
        setupData()

        expect:
        educacionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Educacion> educacionList = educacionService.list(max: 2, offset: 2)

        then:
        educacionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        educacionService.count() == 5
    }

    void "test delete"() {
        Long educacionId = setupData()

        expect:
        educacionService.count() == 5

        when:
        educacionService.delete(educacionId)
        sessionFactory.currentSession.flush()

        then:
        educacionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Educacion educacion = new Educacion()
        educacionService.save(educacion)

        then:
        educacion.id != null
    }
}
