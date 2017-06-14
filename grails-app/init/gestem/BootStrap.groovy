package gestem

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if(Environment.current == Environment.DEVELOPMENT) {
            new Usuario(nombre: "Cristian", paterno: "Pardo", materno: "Velasquez", usuario: "cepardov", clave: "123").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
