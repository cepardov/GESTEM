package gestem

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
class AlumnoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    def index(Integer max, User user) {
        def loggedUserInfo = User.findByUsername(sec.username())
        def institucionName = loggedUserInfo.institucion
        def alumno = User.findAllByIsStudent(true)

        params.max = Math.min(max ?: 20, 100)

        if(params.id!=null){
            respond user, model: [alumnoCount:User.count(), alumnoList:alumno]
        }else{
            respond new User(params), model: [alumnoCount:User.count(), alumnoList:alumno]
        }
    }

    @Secured(['ROLE_LEVEL0','ROLE_LEVEL1'])
    @Transactional
    def save(User user) {
        def loggedUserInfo = User.findByUsername(sec.username())
        def institucion = loggedUserInfo.institucion

        def rut = params.rut
        boolean rutExist = User.findByRut(rut)

        user.setIsStudent(true)
        user.setInstitucion(institucion)

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
                    redirect(controller:"alumno", action: "index")
                }
                '*' { respond user, [status: CREATED] }
            }
        } else {
            flash.message = "El susario con RUT:"+rut+" ya existe."
            redirect (controller: "alumno", action: "index")
        }
    }

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
