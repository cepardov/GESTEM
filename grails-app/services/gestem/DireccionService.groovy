package gestem

import grails.gorm.services.Service

@Service(Direccion)
interface DireccionService {

    Direccion get(Serializable id)

    List<Direccion> list(Map args)

    Long count()

    void delete(Serializable id)

    Direccion save(Direccion direccion)

}