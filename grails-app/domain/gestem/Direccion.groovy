package gestem

class Direccion {
    String address

    static belongsTo = [comuna:Comuna, user:User]

    static constraints = {

    }
}
