package gestem

class Correo {
    String email

    static belongsTo = [user:User]

    static constraints = {
        email nullable: true, blank: true
    }
}
