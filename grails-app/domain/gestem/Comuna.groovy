package gestem

class Comuna {
    String codigo
    String nombre

    static hasMany = [sostenedor:Sostenedor]

    static constraints = {
    }
}
