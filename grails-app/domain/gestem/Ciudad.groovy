package gestem

class Ciudad {
    String codigo
    String nombre

    static hasMany = [comuna:Comuna]

    static constraints = {
    }
}
