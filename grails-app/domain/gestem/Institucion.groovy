package gestem

class Institucion {
    String code
    String name

    static belongsTo = [sostenedor:Sostenedor]

    static constraints = {
    }
}
