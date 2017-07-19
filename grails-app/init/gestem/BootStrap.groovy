package gestem

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if(Environment.current == Environment.DEVELOPMENT) {
            new Usuario(rut: "172132332", nombre: "Cristian", paterno: "Pardo", materno: "Velasquez", usuario: "cepardov", clave: "123").save(failOnError: true)
            new Pais(code: "CL", name: "Chile").save(failOnError: true)
            new Region(code: "X", name: "Los Lagos", pais: "1").save(failOnError: true)
            new Ciudad(code: "LLH", name: "Llanquihue", region: "1").save(failOnError: true)
            new Comuna(code: "PVS", name: "Puerto Varas", ciudad: "1").save(failOnError: true)
            new Sostenedor(code: "69220200", name: "I. Municipalidad Puerto Varas", comuna: "1").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
