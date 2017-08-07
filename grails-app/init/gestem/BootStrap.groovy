package gestem

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        printf('Init BootStrap...\n')
        if(Environment.current == Environment.DEVELOPMENT) {
            //Roles
            def superRole = new Role(authority: 'ROLE_SUPERADMIN', name: 'Super Usuario', description: 'Super Usuario').save(failOnError: true)
            def adminRole = new Role(authority: 'ROLE_ADMIN', name: 'Administrador', description: 'Usuario admiinistrador').save(failOnError: true)

            //Users
            def superUser = new User(rut: "172132332", nombre: "Cristian", segNombre: "Esteban", paterno: "Pardo", materno: "Velasquez", username: 'admin', password: 'admin').save(failOnError: true)

            UserRole.create superUser, superRole

            new Correo(email: "cepardov@gmail.com", user: "1").save(failOnError: true)
            new Telefono(phoneNumber: "123123123", user: "1").save(failOnError: true)
            new Pais(code: "CL", name: "Chile").save(failOnError: true)
            new Region(code: "X", name: "Los Lagos", pais: "1").save(failOnError: true)
            new Ciudad(code: "LLH", name: "Llanquihue", region: "1").save(failOnError: true)
            new Comuna(code: "PVS", name: "Puerto Varas", ciudad: "1").save(failOnError: true)
            new Direccion(address: "Siempre Viva 123", user: "1", comuna: "1").save(failOnError: true)
            new Sostenedor(code: "69220200", name: "I. Municipalidad Puerto Varas", comuna: "1").save(failOnError: true)

            //printf('User ID:'+User.findByUsername('admin').id)
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }
        printf('...Init BootStrap\n')
    }
    def destroy = {
    }
}
