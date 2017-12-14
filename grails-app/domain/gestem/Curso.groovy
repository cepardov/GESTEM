package gestem

class Curso {

    String code
    String name

    static hasMany = [user:User,asignatura:Asignatura,user:User]
    static belongsTo = [educacion:Educacion]

    static constraints = {
    }
}
