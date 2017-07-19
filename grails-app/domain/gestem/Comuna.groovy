package gestem

class Comuna {
    String code
    String name

    static hasMany = [sostenedor:Sostenedor]
    static belongsTo = [ciudad:Ciudad]

    static constraints = {
    }
}
