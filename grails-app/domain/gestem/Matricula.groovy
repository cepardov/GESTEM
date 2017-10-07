package gestem

class Matricula {
    Integer num
    Integer year
    Date fechaMatricula

    static belongsTo = [user:User]

    static constraints = {
    }
}
