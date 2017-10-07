package gestem

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        printf('Init BootStrap...\n')
        if(Environment.current == Environment.DEVELOPMENT) {
            new Pais(code: "CL", name: "Chile").save(failOnError: true)
            new Region(code: "X", name: "Los Lagos", pais: "1").save(failOnError: true)
            new Ciudad(code: "LLH", name: "Llanquihue", region: "1").save(failOnError: true)
            new Comuna(code: "PVS", name: "Puerto Varas", ciudad: "1").save(failOnError: true)
            new Sostenedor(code: "69220200", name: "I. Municipalidad Puerto Varas", comuna: "1").save(failOnError: true)
            new Institucion(code: '7720', name: 'Liceo Pedro Aguirre Cerda', sostenedor: "1").save(failOnError: true)
            new Institucion(code: '7721', name: 'Rosita Novaro', sostenedor: '1').save(failOnError: true)


            //Roles
            def superRole = new Role(authority: 'ROLE_LEVEL0', name: 'Super Usuario', description: 'Super Usuario').save(failOnError: true)
            def adminRole = new Role(authority: 'ROLE_LEVEL1', name: 'Administrador', description: 'Usuario administrador').save(failOnError: true)

            new Role(authority: 'ROLE_LEVEL2', name: 'UTP', description: 'Unidad Técnico Pedagogica').save(failOnError: true)
            new Role(authority: 'ROLE_LEVEL3', name: 'Coordinador Departamento', description: 'Jefe de departamento').save(failOnError: true)
            new Role(authority: 'ROLE_LEVEL4', name: 'Profesor Jefe', description: 'Profesor Jefe').save(failOnError: true)
            new Role(authority: 'ROLE_LEVEL5', name: 'Profesor Asignatura', description: 'Profesor Asignatura').save(failOnError: true)
            new Role(authority: 'ROLE_LEVEL6', name: 'Apoderado', description: 'Apoderado').save(failOnError: true)



            //Users
            def superUser = new User(rut: "17213233-2", nombre: "Cristian", segNombre: "Esteban", paterno: "Pardo", materno: "Velasquez", userType: '1', username: 'admin', password: 'admin').save(failOnError: true)
            def adminUserOne = new User(rut: "14625724-0", nombre: "Alex", segNombre: "Carlos", paterno: "Cartes", materno: "Perez", userType: '1', username: 'acartes', password: '123', institucion: '1').save(failOnError: true)

            printf('superRole='+superRole+'\n')
            printf('superUser='+superUser+'\n')

            new Correo(email: "cepardov@gmail.com", user: "1").save(failOnError: true)
            new Telefono(phoneNumber: "123123123", user: "1").save(failOnError: true)
            new Direccion(address: "Siempre Viva 123", user: "1", comuna: "1").save(failOnError: true)



            UserRole.create superUser, superRole
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
