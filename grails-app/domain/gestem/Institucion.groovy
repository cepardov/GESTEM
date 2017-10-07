package gestem

class Institucion {
    String code
    String name

    static hasMany = [user:User]
    static belongsTo = [sostenedor:Sostenedor]

    static constraints = {
    }
}
