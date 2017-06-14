package gestem

class Sostenedor {
    String idSostenedor
    String nombre

    static hasMany = [institucion:Institucion]

    static constraints = {
    }
}
