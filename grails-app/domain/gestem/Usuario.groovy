package gestem

class Usuario {
    String rut
    String nombre
    String paterno
    String materno
    String usuario
    String clave

    static constraints = {
        //rut unique: true
        //usuario unique:true
        //nombre blank:false
        //paterno  blank:false
        //materno blank:false
        //username  blank:false, size:5..15, matches:/[\S]+/, unique:true
        //clave  blank:false
    }
}
