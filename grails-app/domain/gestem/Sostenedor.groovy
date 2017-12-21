package gestem

class Sostenedor {
    String code
    String name

    static hasMany = [institucion:Institucion]
    static belongsTo = [comuna:Comuna]

    static constraints = {
        code unique: true, blank: false, nullable: false
    }
}
