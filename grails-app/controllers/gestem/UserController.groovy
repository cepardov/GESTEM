package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    @Secured('ROLE_SUPERADMIN')
    def index(Integer max,User user) {
        printf('User='+springSecurityService.currentUserId)
        def users = User.list(params)
        params.max = Math.min(max ?: 10, 100)
        if(params.id!=null){
            respond user, model:[userCount: User.count(), userList:users]
        }else{
            respond new User(params), model:[userCount: User.count(), userList:users]
        }

    }

    def getFechaNacimiento(User user){
        def fechaNacimiento
        printf('\ngetFechaNacimiento in:'+user.fechaNacimiento)
        printf('\nid user:'+user.id)

        if(user.fechaNacimiento){
            def fechaNacimientoIn = user.fechaNacimiento.toString().replace(' ',' | ')
            printf('\nFecha remplace:'+fechaNacimientoIn)
            String[] fechaNacimientoSplit0 = fechaNacimientoIn.split(' | ')
            String[] fechaNacimientoSplit1 = fechaNacimientoSplit0[0].split('-')

            def dd = fechaNacimientoSplit1[2]
            def mm = fechaNacimientoSplit1[1]
            def yyyy = fechaNacimientoSplit1[0]

            fechaNacimiento = dd+'/'+mm+'/'+yyyy
            printf('\nFinal Fecha:'+ fechaNacimiento)

        }

        return fechaNacimiento
    }

    @Secured('ROLE_SUPERADMIN')
    def show(User user) {
        def direccionList = Direccion.findAllByUser(User.findById(params.id))
        def telefonoList = Telefono.findAllByUser(User.findById(params.id))
        def correoList = Correo.findAllByUser(User.findById(params.id))
        def direccion
        def telefono
        def correo
        if(params.idDireccion){
            direccion = Direccion.findById(params.idDireccion).address
        }
        if(params.idTelefono){
            telefono = Telefono.findById(params.idTelefono).phoneNumber
        }
        if(params.idCorreo){
            correo = Correo.findById(params.idCorreo).email
        }
        respond user, model:[direccionList:direccionList, direccion:direccion, telefonoList:telefonoList, telefono:telefono, correoList:correoList, correo:correo, fechaNacimientoOut: getFechaNacimiento()]
    }

    @Secured('ROLE_SUPERADMIN')
    def create() {
        respond new User(params)
    }

    @Secured('ROLE_SUPERADMIN')
    @Transactional
    def save(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id, user.nombre, user.paterno, user.materno])
                redirect(controller:"user", action: "index")
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    //def edit(User user) {
    //    respond user
    //}

    @Secured('ROLE_SUPERADMIN')
    def eliminar(){
        def user = User.get(params.id)
        user.delete(flush:true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id, user.nombre, user.paterno, user.materno])
        redirect (controller: "user", action: "index")
    }

    @Secured('ROLE_SUPERADMIN')
    @Transactional
    def update(User user) {
        if(params.fechaNacimientoDat){
            printf('\nFecha Inicial:'+params.fechaNacimientoDat+'\n')
            String[] fechaNacimientoSplit = ((String) params.fechaNacimientoDat).split('/')
            def dd = fechaNacimientoSplit[0]
            def mm = fechaNacimientoSplit[1]
            def yyyy = fechaNacimientoSplit[2]
            Date date = new Date().parse("d/M/yyyy H:m:s", dd+'/'+mm+'/'+yyyy+' 00:00:00.0')
            user.setFechaNacimiento(date)
        } else {

        }

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'index'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id, user.nombre, user.paterno, user.materno])
                redirect(controller:"user", action: "index")
            }
            '*'{ respond user, [status: OK] }
        }
    }

    @Secured('ROLE_SUPERADMIN')
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
