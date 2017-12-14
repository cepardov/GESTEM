package gestem

import grails.gorm.services.Service

@Service(Correo)
interface CorreoService {

    Correo get(Serializable id)

    List<Correo> list(Map args)

    Long count()

    void delete(Serializable id)

    Correo save(Correo correo)

}