package gestem

class Pais {
    String code
    String name

    static hasMany = [region:Region]

    static constraints = {
        code unique: true, blank: false, nullable: false
    }
}
