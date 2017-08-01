package gestem

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'admin', password: '123').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }
        if(Environment.current == Environment.DEVELOPMENT) {
            new Funcion(code: "001", name: "Super Admin", descripcion: "Cuenta administrador").save(failOnError: true)
            new Usuario(rut: "172132332", nombre: "Cristian", paterno: "Pardo", materno: "Velasquez", usuario: "cepardov", clave: "123", funcion: "1").save(failOnError: true)
            new Correo(email: "cepardov@gmail.com", usuario: "1").save(failOnError: true)
            new Telefono(phoneNumber: "123123123", usuario: "1").save(failOnError: true)
            new Pais(code: "CL", name: "Chile").save(failOnError: true)
            new Region(code: "X", name: "Los Lagos", pais: "1").save(failOnError: true)
            new Ciudad(code: "LLH", name: "Llanquihue", region: "1").save(failOnError: true)
            new Comuna(code: "PVS", name: "Puerto Varas", ciudad: "1").save(failOnError: true)
            new Direccion(address: "Siempre Viva 123", usuario: "1", comuna: "1").save(failOnError: true)
            new Sostenedor(code: "69220200", name: "I. Municipalidad Puerto Varas", comuna: "1").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
