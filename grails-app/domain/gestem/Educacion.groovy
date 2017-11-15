package gestem

class Educacion {
    String code
    String name
    Date fRes
    String description
    Boolean enable

    static constraints = {
        fRes blank:true, nullable: true
        description blank:true, nullable: true
        enable blank:true, nullable: true
    }
}
