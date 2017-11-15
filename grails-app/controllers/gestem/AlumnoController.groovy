package gestem

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_LEVEL0','ROLE_LEVELX'])
class AlumnoController {

    def index() {

    }

    def list(Integer max, User user) {


        def loggedUserInfo = User.findByUsername(sec.username())

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


        params.max = Math.min(max ?: 10, 100)

        if(params.id!=null){
            respond user, model:[userCount: userCount, userList:userList, institucionList:institucionList]
        }else{
            respond new User(params), model:[userCount: userCount, userList:userList, institucionList:institucionList]
        }
    }

    def show(){

    }
}
