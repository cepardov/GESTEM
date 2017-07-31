package gestem

class Telefono {
    String phoneNumber

    static belongsTo = [usuario:Usuario]

    static constraints = {
    }
}
