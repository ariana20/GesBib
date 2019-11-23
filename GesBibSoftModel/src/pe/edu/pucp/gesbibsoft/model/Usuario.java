package pe.edu.pucp.gesbibsoft.model;


public class Usuario {
    //ATRIBUTOS PROPIOS
    private int id=-1;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    
    //<<-- agregar mas atributos SI ven convenientes
    //CONSTRUCTORES
    public Usuario() {}

    public Usuario(String nombre, String apellido,  String email, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
    }
    
    public int getId() {
        return id;
    }

    //SETS AND GETS
    public void setId(int id) {    
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    //METODOS

    public void actualizarContrasenia(String contrasenia){};
    public void modificarInformacionPersonal(String nombre, String apellido, String email){};
    
}
