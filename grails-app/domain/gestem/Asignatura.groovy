package gestem

class Asignatura {
    String code
    String name
    String description

    static belongsTo = [educacion:Educacion]

    static constraints = {
        code unique: true, blank: false, nullable: false
        description nullable: true, blank: true
    }
}
