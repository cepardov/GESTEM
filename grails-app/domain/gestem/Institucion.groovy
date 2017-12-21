package gestem

class Institucion {
    String code
    String name
    String nRes
    Date fRes
    String phone
    String address

    static hasMany = [user:User, educacion:Educacion]
    static belongsTo = [sostenedor:Sostenedor]

    static constraints = {
        code unique: true, blank: false, nullable: false
        nRes blank:true, nullable: true
        fRes blank:true, nullable: true
        phone blank:true, nullable: true
        address blank:true, nullable: true
    }
}
