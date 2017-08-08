package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
@Transactional(readOnly = true)
class UserTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    def index(Integer max,UserType userType) {
        def userTypes = UserType.list(params)
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond userType, model:[userTypeCount: UserType.count(), userTypeList:userTypes]
        }else{
            respond new UserType(params), model:[userTypeCount: UserType.count(), userTypeList:userTypes]
        }
    }

    def show(UserType userType) {
        respond(userType)
    }

    def create() {
        respond new UserType(params)
    }

    @Transactional
    def save(UserType userType) {
        if (userType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userType.errors, view:'create'
            return
        }

        userType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userType.label', default: 'UserType'), userType.id, '', '', ''])
                redirect(controller:"userType", action: "index")
            }
            '*' { respond userType, [status: CREATED] }
        }
    }

    def edit(UserType userType) {
        respond userType
    }

    def eliminar(){
        def userType = UserType.get(params.id)

        userType.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'userType.label', default: 'UserType'), userType.id,'', '', ''])

        redirect (controller: "userType", action: "index")
    }

    @Transactional
    def update(UserType userType) {
        if (userType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userType.errors, view:'edit'
            return
        }

        userType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userType.label', default: 'UserType'), userType.id, '', '', ''])
                redirect(controller:"userType", action: "index")
            }
            '*'{ respond userType, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userType.label', default: 'UserType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
