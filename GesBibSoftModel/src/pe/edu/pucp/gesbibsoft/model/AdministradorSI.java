package pe.edu.pucp.gesbibsoft.model;

public class AdministradorSI extends Usuario{
   
    //CONSTRUCTORES    
    public AdministradorSI() {}
    
    public AdministradorSI(String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido,  email, contrasenia);
    }
       
    //SETS AND GETS

    

    //METODOS....
    public void crearGestor(){}
    public void actualizarGestor(){}
    public void eliminarGestor(){}
    
    public void crearPracticantes(){}
    public void actualizarPracticantes(){}
    public void eliminarPracticantes(){}
    
    public void crearPersonalAuxiliar(){}
    public void actualizarPersonalAuxiliar(){}
    public void eliminarPersonalAuxiliar(){}
   
    
    public void crearBibliotecarios(){}
    public void actualizarBibliotecarios(){}
    public void eliminarBibliotecarios(){}
   
    
    public void crearBibliotecas(){}
    public void actualizarBibliotecas(){}
    public void eliminarBibliotecas(){}
}
