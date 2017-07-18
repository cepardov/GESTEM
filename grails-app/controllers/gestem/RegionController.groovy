package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RegionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max,Region region) {
        //printf('\npais:'+params.pais)
        def regionsByPais = Region.findAllByPais(Pais.findById(params.paisId))
        def regions = Region.list(params)
        def pais = Pais.findAll()

        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond region, model:[regionCount: Region.count(), regionList:regions]
        }else if(params.paisId!=null) {
            respond new Region(params), model: [regionCount: Region.countByPais(Pais.findById(params.paisId)), regionList: regionsByPais, paisList:pais]
        }else {
            respond new Region(params), model:[regionCount: Region.count(), regionList:regions, paisList:pais]
        }

    }

    def show(Region region) {
        redirect(controller:"region", action: "index")
    }

    def create() {
        respond new Region(params)
    }

    @Transactional
    def save(Region region) {
        if (region == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (region.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond region.errors, view:'index'
            return
        }

        region.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), region.id, region.code, region.name, ''])
                redirect(controller:"region", action: "index")
            }
            '*' { respond region, [status: CREATED] }
        }
    }

    def edit(Region region) {
        respond region
    }

    def eliminar(){
        def region = Region.get(params.id)
        region.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), region.id, region.code, region.name, ''])
        redirect (controller: "region", action: "index")
    }

    @Transactional
    def update(Region region) {
        if (region == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (region.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond region.errors, view:'edit'
            return
        }

        region.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), region.id, region.code, region.name, ''])
                redirect(controller:"region", action: "index")
            }
            '*'{ respond region, [status: OK] }
        }
    }

    @Transactional
    def delete(Region region) {

        if (region == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        region.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), region.id, region.code, region.name, ''])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
