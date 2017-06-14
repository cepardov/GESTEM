package gestem

class Institucion {
    String rbd
    String rut
    String nombre
    String region
    String comuna

    static hasMany = [funcionario:Funcionario]

    static constraints = {
    }
}
