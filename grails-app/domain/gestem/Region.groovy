package gestem

class Region {
    String code
    String name

    static hasMany = [ciudad:Ciudad]
    static belongsTo = [pais:Pais]

    static constraints = {
    }
}
