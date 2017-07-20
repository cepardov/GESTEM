package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FuncionarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Funcionario.list(params), model:[funcionarioCount: Funcionario.count()]
    }

    def show(Funcionario funcionario) {
        respond funcionario
    }

    def create() {
        respond new Funcionario(params)
    }

    @Transactional
    def save(Funcionario funcionario) {
        if (funcionario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (funcionario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond funcionario.errors, view:'create'
            return
        }

        funcionario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'funcionario.label', default: 'Funcionario'), funcionario.id])
                redirect funcionario
            }
            '*' { respond funcionario, [status: CREATED] }
        }
    }

    def edit(Funcionario funcionario) {
        respond funcionario
    }

    @Transactional
    def update(Funcionario funcionario) {
        if (funcionario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (funcionario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond funcionario.errors, view:'edit'
            return
        }

        funcionario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'funcionario.label', default: 'Funcionario'), funcionario.id])
                redirect funcionario
            }
            '*'{ respond funcionario, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'funcionario.label', default: 'Funcionario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
