package gestem

class Telefono {
    Integer phoneNumber

    static belongsTo = [usuario:Usuario]

    static constraints = {
        phoneNumber nullable: true, blank: true
    }
}
