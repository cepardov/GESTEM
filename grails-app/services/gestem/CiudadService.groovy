package gestem

import grails.gorm.services.Service

@Service(Ciudad)
interface CiudadService {

    Ciudad get(Serializable id)

    List<Ciudad> list(Map args)

    Long count()

    void delete(Serializable id)

    Ciudad save(Ciudad ciudad)

}