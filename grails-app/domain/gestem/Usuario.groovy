package gestem

class Usuario {
    String rut
    String nombre
    String paterno
    String materno
    Date fechaNacimiento
    String genero
    String nacionalidad
    String usuario
    String clave

    static hasMany = [direccion:Direccion, telefono:Telefono, correo:Correo]
    static belongsTo = [funcion:Funcion]

    static constraints = {
        fechaNacimiento blank:true, nullable: true
        genero blank:true, nullable: true
        nacionalidad blank:true, nullable: true
        funcion nullable: true
        //rut unique: true
        //usuario unique:true
        //nombre blank:false
        //paterno  blank:false
        //materno blank:false
        //username  blank:false, size:5..15, matches:/[\S]+/, unique:true
        //clave  blank:false
    }
}
