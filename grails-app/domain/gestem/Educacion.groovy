package gestem

class Educacion {
    String code
    String name
    String fRes
    String description
    Boolean enable

    static hasMany = [cursos:Curso, asignaturas:Asignatura]
    static belongsTo = [institucion:Institucion]

    static constraints = {
        code unique: true, blank: false, nullable: false
        fRes blank:true, nullable: true
        description blank:true, nullable: true
        enable blank:true, nullable: true
    }

    String toString(){
        return name
    }
}
