package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_SUPERADMIN')
@Transactional(readOnly = true)
class UserRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def idUser

    def index(Integer max,UserRole userRole) {
        def userRoleByUser = UserRole.findAllByUser(User.findById(params.idUser))
        def userRoles = UserRole.list(params)

        idUser = params.idUser

        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            if(params.idUser!=null){
                respond userRole, model:[userRoleCount: UserRole.countByUser(User.findById(params.idUser)), userRoleList: userRoleByUser]
            } else {
                respond userRole, model:[userRoleCount: UserRole.count(), userRoleList:userRoles]
            }
        } else if(params.idUser!=null){
            respond new UserRole(params), model:[userRoleCount: UserRole.countByUser(User.findById(params.idUser)), userRoleList: userRoleByUser]
        } else {
            respond new UserRole(params), model:[userRoleCount: UserRole.count(), userRoleList:userRoles]
        }

    }

    def show(UserRole userRole) {
        respond userRole
    }

    def create() {
        respond new UserRole(params)
    }

    @Transactional
    def save(UserRole userRole) {
        if (userRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userRole.errors, view:'create'
            return
        }

        userRole.save flush:true

        printf('\nReturn:'+params.r)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRole.id])

                if(params.r != "showUser"){
                    redirect (controller: "userRole", action: "index")
                } else {
                    redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*' { respond userRole, [status: CREATED] }
        }
    }

    def edit(UserRole userRole) {
        respond userRole
    }

    def eliminar(){
        def role = params.idRole
        def user = params.idUser
        UserRole.remove(User.findById(user),Role.findById(role))
        //userRole.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'userRole.label', default: 'UserRole'), ''])
        if(params.r != "showUser"){
            redirect (controller: "userRole", action: "index")
        } else {
            redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
        }
    }

    @Transactional
    def update(UserRole userRole) {
        if (userRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userRole.errors, view:'edit'
            return
        }

        userRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userRole.label', default: 'UserRole'), userRole.id])
                if(params.r != "showUser"){
                    redirect (controller: "userRole", action: "index")
                } else {
                    redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
                }
            }
            '*'{ respond userRole, [status: OK] }
        }
    }

    def delete(){
        eliminar()
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRole.label', default: 'UserRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
