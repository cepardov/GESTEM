package gestem

class Region {
    String codigo
    String nombre

    static hasMany = [ciudad:Ciudad]

    static constraints = {
    }
}
