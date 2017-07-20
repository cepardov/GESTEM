package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CiudadController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def regionId
    def regionName

    def index(Integer max,Ciudad ciudad) {
        def ciudadsByRegion = Ciudad.findAllByRegion(Region.findById(params.regionId))
        def ciudads = Ciudad.list(params)
        def region = Region.findAll()

        regionId = params.regionId
        regionName = params.regionName

        params.max = Math.min(max ?: 20, 100)

        if(params.id!=null){
            respond ciudad, model:[ciudadCount: Ciudad.count(), ciudadList:ciudads]
        }else if(params.regionId!=null) {
            respond new Ciudad(params), model: [ciudadCount: Ciudad.countByRegion(Region.findById(params.regionId)), ciudadList: ciudadsByRegion, regionList:region]
        }else {
            respond new Ciudad(params), model:[ciudadCount: Ciudad.count(), ciudadList:ciudads, regionList:region]
        }

    }

    def show(Ciudad ciudad) {
        redirect(controller:"ciudad", action: "index")
    }

    def create() {
        respond new Ciudad(params)
    }

    @Transactional
    def save(Ciudad ciudad) {
        if (ciudad == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ciudad.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ciudad.errors, view:'index'
            return
        }

        ciudad.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudad.id, ciudad.code, ciudad.name, ''])
                redirect(controller:"ciudad", action: "index", params: [regionId: regionId,regionName: regionName])
            }
            '*' { respond ciudad, [status: CREATED] }
        }
    }

    def edit(Ciudad ciudad) {
        respond ciudad
    }

    def eliminar(Ciudad ciudad){
        //def ciudad = Ciudad.get(params.id)
        ciudad.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudad.id, ciudad.code, ciudad.name, ''])
        redirect (controller: "ciudad", action: "index", params: [regionId: regionId,regionName: regionName])
    }

    @Transactional
    def update(Ciudad ciudad) {
        if (ciudad == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ciudad.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ciudad.errors, view:'edit'
            return
        }

        ciudad.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), ciudad.id, ciudad.code, ciudad.name, ''])
                redirect(controller:"ciudad", action: "index",  params: [regionId: regionId,regionName: regionName])
            }
            '*'{ respond ciudad, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ciudad.label', default: 'Ciudad'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
