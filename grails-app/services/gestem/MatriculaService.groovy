package gestem

import grails.gorm.services.Service

@Service(Matricula)
interface MatriculaService {

    Matricula get(Serializable id)

    List<Matricula> list(Map args)

    Long count()

    void delete(Serializable id)

    Matricula save(Matricula matricula)

}