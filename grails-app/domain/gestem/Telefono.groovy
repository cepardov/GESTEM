package gestem

class Telefono {
    String phoneNumber

    static belongsTo = [user:User]

    static constraints = {
    }
}
