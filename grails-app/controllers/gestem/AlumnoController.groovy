package gestem

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
class AlumnoController {

    def index(Integer max, User user) {
        println "\n\nHello\n\n"
        def loggedUserInfo = User.findByUsername(sec.username())
        def institucionName = loggedUserInfo.institucion
        def alumno = User.findAllByIsStudent(true)
        //def alumno = UserRole.findAllByUserAndRole(User.findAllByInstitucion(institucionName),Role.findByName('Apoderado').id)


        params.max = Math.min(max ?: 20, 100)

        println "\n\nAlumno Index: Alumnos ="+User.count()+" registros\n"
        respond new User(params), model: [alumnoCount:User.count(), alumnoList:alumno]
    }

    @Secured(['ROLE_LEVEL0','ROLE_LEVEL1'])
    @Transactional
    def save(User user) {
        def rut = params.rut
        boolean rutExist = User.findByRut(rut)

        //user.setIsStudent(true)

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
}
