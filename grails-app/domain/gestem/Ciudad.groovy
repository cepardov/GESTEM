package gestem

class Ciudad {
    String code
    String name

    static hasMany = [comuna:Comuna]
    static belongsTo = [region:Region]

    static constraints = {
    }
}
