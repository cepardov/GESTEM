package gestem

class Correo {
    String email

    static belongsTo = [usuario:Usuario]

    static constraints = {
        email nullable: true, blank: true
    }
}
