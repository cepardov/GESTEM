package gestem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_SUPERADMIN')
@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    @Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
    def index(Integer max,User user) {
        def loggedUserInfo = User.findByUsername(sec.username())
        printf('loggedUserInfo='+loggedUserInfo.institucion+'\n')
        def query = params.q.toString().length()
        def userList
        def userCount
        def institucionList

        if(params.q && query>2){
            def userCriteria = User.createCriteria()
            userList = userCriteria.list(params){
                if(params.q){
                    or{
                        ilike('username','%'+params.q+'%')
                        ilike('nombre','%'+params.q+'%')
                        ilike('segNombre','%'+params.q+'%')
                        ilike('paterno','%'+params.q+'%')
                        ilike('materno','%'+params.q+'%')
                    }
                }
            }
            userCount = userList.findAll().size()
        } else {
            if(params.q){
                flash.message = "El termino de búsqueda debe ser mayor a 3 caracteres"
            }
            if(!loggedUserInfo.institucion){
                userList = User.list(params)
                institucionList = Institucion.list()
                userCount = User.count()
            } else {
                userList = User.findAllByInstitucion(loggedUserInfo.institucion)
                institucionList = Institucion.findAllByCode(loggedUserInfo.institucion.code)
                userCount = userList.size()
            }
        }

        //def users = User.list(params)
        def userType = UserType.list()

        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            respond user, model:[userCount: userCount, userList:userList, userTypeList:userType, institucionList:institucionList]
        }else{
            respond new User(params), model:[userCount: userCount, userList:userList, userTypeList:userType, institucionList:institucionList]
        }
    }

    def getFechaNacimiento(User user){
        def fechaNacimiento

        if(user.fechaNacimiento){
            def fechaNacimientoIn = user.fechaNacimiento.toString().replace(' ',' | ')
            String[] fechaNacimientoSplit0 = fechaNacimientoIn.split(' | ')
            String[] fechaNacimientoSplit1 = fechaNacimientoSplit0[0].split('-')

            def dd = fechaNacimientoSplit1[2]
            def mm = fechaNacimientoSplit1[1]
            def yyyy = fechaNacimientoSplit1[0]

            fechaNacimiento = dd+'/'+mm+'/'+yyyy

        }
        return fechaNacimiento
    }

    @Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
    def show(User user) {
        if(params.id!=null){
            def direccionList = Direccion.findAllByUser(User.findById(params.id))
            def telefonoList = Telefono.findAllByUser(User.findById(params.id))
            def correoList = Correo.findAllByUser(User.findById(params.id))
            def userRoleList = UserRole.findAllByUser(User.findAllById(params.id))
            def roleList = Role.findAll()
            def direccion
            def telefono
            def correo
            def userRole
            if(params.idDireccion){
                direccion = Direccion.findById(params.idDireccion).address
            }
            if(params.idTelefono){
                telefono = Telefono.findById(params.idTelefono).phoneNumber
            }
            if(params.idCorreo){
                correo = Correo.findById(params.idCorreo).email
            }
            if(params.idRole){
                userRole = UserRole.findAllById(params.idRole).id
            }
            respond user, model:[
                    direccionList:direccionList,
                    direccion:direccion,
                    telefonoList:telefonoList,
                    telefono:telefono,
                    correoList:correoList,
                    correo:correo,
                    userRoleList:userRoleList,
                    userRole:userRole,
                    fechaNacimientoOut: getFechaNacimiento(),
                    roleList:roleList
            ]
        } else {
            redirect(controller:"user", action: "index")
        }
    }
    
    def create() {
        respond new User(params)
    }

    @Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
    @Transactional
    def save(User user) {
        printf('\nValidación de rut\n')
        def rut = params.rut
        printf('Params rut='+rut+'\n')
        boolean rutExist = User.findByRut(rut)

        if(!rutExist){
            if (user == null) {
                transactionStatus.setRollbackOnly()
                notFound()
                return
            }

            if (user.hasErrors()) {
                transactionStatus.setRollbackOnly()
                respond user.errors, view:'show'
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
        } else {
            flash.message = "El susario con RUT:"+rut+" ya existe."
            redirect (controller: "user", action: "index")
        }


    }

    def saveRole (){
        def userId = User.findById(params.user)
        def roleId = Role.findById(params.role)

        if(userId == null || roleId == null){
            flash.message = "No se puede realizar la operación, intente más tarde."
        } else {
            UserRole.create(userId,roleId,true)
            flash.message = "Se ha asignado Rol "+roleId.name+" a este usuario correctamente"
        }
        if(params.r != "showUser"){
            redirect (controller: "user", action: "index")
        } else {
            redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
        }
    }

    def deleteRole(){
        def userId = User.findById(params.idUser)
        def roleId = Role.findById(params.idRole)

        if(userId == null || roleId == null){
            flash.message = "No se puede realizar la operación, intente más tarde."
        } else {
            UserRole.remove(userId,roleId)
            flash.message = "Se ha removido el rol"
        }
        if(params.r != "showUser"){
            redirect (controller: "user", action: "index")
        } else {
            redirect(controller: "user", action: "show", id: params.idUser, params: [name : params.name, lastName : params.lastName])
        }
    }

    @Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
    def eliminar(){
        def user = User.get(params.id)
        int roleUser = UserRole.countByUser(user)
        if(roleUser>0){
            flash.message = "Error: Este usuario tiene "+roleUser+" asignados, elimine los roles primero."
        } else {
            user.delete(flush:true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id, user.nombre, user.paterno, user.materno])
        }
        redirect (controller: "user", action: "index")
    }

    @Secured(['ROLE_SUPERADMIN','ROLE_ADMIN'])
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

    protected void notFound() {
        printf('\nNOT FOUND\n')
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
