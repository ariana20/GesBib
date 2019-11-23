package pe.edu.pucp.gesbibsoft.model;
import java.util.ArrayList;

public class PerfilExperiencia {
    // ATRIBUTOS PROPIOS
    private int id;
    private String nombrePerfil;
    private ArrayList<PersonalBiblioteca> listaPersonalBiblioteca;
    private ArrayList<PuntosAtencion> listaPuntosAtencion;
    
    // SETTERS Y GETTERS
    public ArrayList<PersonalBiblioteca> getListaPersonalBiblioteca() {
        return listaPersonalBiblioteca;
    }
    public void addPersonal(PersonalBiblioteca personal) {
        this.listaPersonalBiblioteca.add(personal);
    }
    public void removePersonal(PersonalBiblioteca personal) {
        this.listaPersonalBiblioteca.remove(personal);
    }
    public ArrayList<PuntosAtencion> getListaPuntosAtencion() {
        return listaPuntosAtencion;
    }
    public void addPersonal(PuntosAtencion puntoAtencion) {
        this.listaPuntosAtencion.add(puntoAtencion);
    }
    public void removePersonal(PuntosAtencion puntoAtencion) {
        this.listaPuntosAtencion.remove(puntoAtencion);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombrePerfil() {
        return nombrePerfil;
    }
    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
    
    
    // CONSTRUCTORES
    public PerfilExperiencia() {
        this.listaPersonalBiblioteca = new ArrayList<>();
        this.listaPuntosAtencion = new ArrayList<>();
    }
    public PerfilExperiencia(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
        this.listaPersonalBiblioteca = new ArrayList<>();
        this.listaPuntosAtencion = new ArrayList<>();
    }

    
    
    
    
    
}
