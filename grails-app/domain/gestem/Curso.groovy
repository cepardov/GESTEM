package gestem

class Curso {

    String code
    String name
    int year

    static hasMany = [user:User,asignatura:Asignatura,user:User]
    static belongsTo = [educacion:Educacion]

    static constraints = {
    }

    String toString(){
        return name
    }
}
