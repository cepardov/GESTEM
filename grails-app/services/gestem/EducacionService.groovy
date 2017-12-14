package gestem

import grails.gorm.services.Service

@Service(Educacion)
interface EducacionService {

    Educacion get(Serializable id)

    List<Educacion> list(Map args)

    Long count()

    void delete(Serializable id)

    Educacion save(Educacion educacion)

}