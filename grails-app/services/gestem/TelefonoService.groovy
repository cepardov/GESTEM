package gestem

import grails.gorm.services.Service

@Service(Telefono)
interface TelefonoService {

    Telefono get(Serializable id)

    List<Telefono> list(Map args)

    Long count()

    void delete(Serializable id)

    Telefono save(Telefono telefono)

}