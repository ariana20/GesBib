package pe.edu.pucp.gesbibsoft.model;



import java.util.ArrayList;
import java.util.Date;
//import pe.edu.pucp.gesbibsoft.enums.DiaSemana;

public class PersonalBiblioteca extends Personal {
    // ATRIBUTOS PROPIOS
    private Biblioteca biblioteca;
    //private DiaSemana diaSemana;
    
    // ATRIBUTOS ASOCIACIONES
    public void setListaPerfiles(ArrayList<PerfilExperiencia> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    private ArrayList<PerfilExperiencia> listaPerfiles;
    
    // GETTERS Y SETTERS 
    
    
    public ArrayList<PerfilExperiencia> getListaPerfiles() {
        return listaPerfiles;
    }
    public void addPerfil(PerfilExperiencia perfil) {
        this.listaPerfiles.add(perfil);
    }
    public void removePerfil(PerfilExperiencia perfil) {
        this.listaPerfiles.remove(perfil);
    }
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    
    // CONSTRUCTORES
    public PersonalBiblioteca() {
        super();
        this.listaPerfiles = new ArrayList<>();
    }
    public PersonalBiblioteca(String nombre, String apellido, String codigo, String email, String contrasenia, Date fechaIngreso, float totalHorasExtra) {
        super(nombre, apellido, codigo, email, contrasenia,  fechaIngreso, totalHorasExtra);
        this.listaPerfiles = new ArrayList<>();
    }

    
}
