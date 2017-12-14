package gestem

import grails.gorm.services.Service

@Service(Sostenedor)
interface SostenedorService {

    Sostenedor get(Serializable id)

    List<Sostenedor> list(Map args)

    Long count()

    void delete(Serializable id)

    Sostenedor save(Sostenedor sostenedor)

}