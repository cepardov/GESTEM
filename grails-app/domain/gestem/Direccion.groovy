package gestem

class Direccion {
    String address

    static belongsTo = [usuario:Usuario, comuna:Comuna]

    static constraints = {

    }
}
