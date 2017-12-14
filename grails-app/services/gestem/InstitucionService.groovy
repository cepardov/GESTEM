package gestem

import grails.gorm.services.Service

@Service(Institucion)
interface InstitucionService {

    Institucion get(Serializable id)

    List<Institucion> list(Map args)

    Long count()

    void delete(Serializable id)

    Institucion save(Institucion institucion)

}