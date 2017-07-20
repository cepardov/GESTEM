package gestem

class Institucion {
    String code
    String name

    static hasMany = [funcionario:Funcionario]
    static belongsTo = [sostenedor:Sostenedor]

    static constraints = {
    }
}
