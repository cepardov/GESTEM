package gestem

import grails.gorm.services.Service

@Service(Asignatura)
interface AsignaturaService {

    Asignatura get(Serializable id)

    List<Asignatura> list(Map args)

    Long count()

    void delete(Serializable id)

    Asignatura save(Asignatura asignatura)

}