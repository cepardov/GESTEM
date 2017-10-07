package gestem

class Sostenedor {
    String code
    String name

    static hasMany = [institucion:Institucion]
    static belongsTo = [comuna:Comuna]

    static constraints = {
    }
}
