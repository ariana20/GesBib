package pe.edu.pucp.gesbibsoft.model;



import java.time.temporal.TemporalAccessor;
import java.util.Date;


public class Auxiliar extends PersonalBiblioteca{
    
    public Auxiliar() {
    }
    
    public Auxiliar(String nombre, String apellido, String codigo, String email, String contrasenia, Date fechaIngreso, float totalHorasExtra) {
        super(nombre, apellido, codigo, email, contrasenia,fechaIngreso, totalHorasExtra );
    }
    
    
    
}
