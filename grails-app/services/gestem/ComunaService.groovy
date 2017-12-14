package gestem

import grails.gorm.services.Service

@Service(Comuna)
interface ComunaService {

    Comuna get(Serializable id)

    List<Comuna> list(Map args)

    Long count()

    void delete(Serializable id)

    Comuna save(Comuna comuna)

}