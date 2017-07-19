package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PaisController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max,Pais pais) {
        def paiss = Pais.list(params)
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond pais, model:[paisCount: Pais.count(), paisList:paiss]
        }else{
            respond new Pais(params), model:[paisCount: Pais.count(), paisList:paiss]
        }

    }

    def show(Pais pais) {
        redirect(controller:"pais", action: "index")
    }

    def create() {
        respond new Pais(params)
    }

    @Transactional
    def save(Pais pais) {
        if (pais == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pais.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pais.errors, view:'create'
            return
        }

        pais.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id, pais.code, pais.name, ''])
                redirect(controller:"pais", action: "index")
            }
            '*' { respond pais, [status: CREATED] }
        }
    }

    def edit(Pais pais) {
        respond pais
    }

    def eliminar(){
        def pais = Pais.get(params.id)
        pais.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id, pais.code, pais.name, ''])
        redirect (controller: "pais", action: "index")
    }

    @Transactional
    def update(Pais pais) {
        if (pais == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pais.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pais.errors, view:'edit'
            return
        }

        pais.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id, pais.code, pais.name, ''])
                redirect(controller:"pais", action: "index")
            }
            '*'{ respond pais, [status: OK] }
        }
    }

    @Transactional
    def delete(Pais pais) {

        if (pais == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pais.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pais.label', default: 'Pais'), pais.id, pais.code, pais.name])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pais.label', default: 'Pais'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}