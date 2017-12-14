package gestem

import grails.gorm.services.Service

@Service(Enseñanza)
interface EnseñanzaService {

    Enseñanza get(Serializable id)

    List<Enseñanza> list(Map args)

    Long count()

    void delete(Serializable id)

    Enseñanza save(Enseñanza enseñanza)

}