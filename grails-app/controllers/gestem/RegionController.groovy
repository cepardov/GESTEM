package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_SUPERADMIN')
@Transactional(readOnly = true)
class RegionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def paisId
    def paisName

    
    def index(Integer max,Region region) {
        def regionsByPais = Region.findAllByPais(Pais.findById(params.paisId))
        def regions = Region.list(params)
        def pais = Pais.findAll()

        paisId = params.paisId
        paisName = params.paisName

        params.max = Math.min(max ?: 20, 100)

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
                redirect(controller:"region", action: "index", params: [paisId: paisId,paisName: paisName])
            }
            '*' { respond region, [status: CREATED] }
        }
    }

    
    def edit(Region region) {
        respond region
    }

    
    def eliminar(Region region){
        //def region = Region.get(params.id)
        region.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), region.id, region.code, region.name, ''])
        redirect (controller: "region", action: "index", params: [paisId: paisId,paisName: paisName])
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
                redirect(controller:"region", action: "index",  params: [paisId: paisId,paisName: paisName])
            }
            '*'{ respond region, [status: OK] }
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
