package gestem

class Usuario {
    String nombre
    String paterno
    String materno
    String usuario
    String clave

    static constraints = {
        usuario unique:true;
        nombre blank:false;
        paterno  blank:false;
        materno blank:false;
        //username  blank:false, size:5..15, matches:/[\S]+/, unique:true
        clave  blank:false;
    }
}
