package pe.edu.pucp.gesbibsoft.model;


import java.util.ArrayList;

public class Biblioteca {
    // ATRIBUTOS PROPIOS
    private int id;
    private String nombre;


    private Gestor gestor;


    // ATRIBUTOS ASOCIACIONES

    private ArrayList<PuntosAtencion> listaPuntosAtencion;
    private ArrayList<PersonalBiblioteca> listaPersonal;

    //CONSTRUCTORES
    
    public Biblioteca( String nombre) {
        this.nombre = nombre;
        this.listaPuntosAtencion = new ArrayList<>();
        this.listaPersonal = new ArrayList<>();
        //this.gestor=new Gestor();
    }
    
    public Biblioteca() {
        this.listaPuntosAtencion = new ArrayList<>();
        this.listaPersonal = new ArrayList<>();
        //this.gestor=new Gestor();
    }
    
    
    //SETS AND GETS
    
    public void setListaPersonal(ArrayList<PersonalBiblioteca> listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    public void setListaPuntosAtencion(ArrayList<PuntosAtencion> listaPuntosAtencion) {
        this.listaPuntosAtencion = listaPuntosAtencion;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<PuntosAtencion> getListaPuntosAtencion() {
        return listaPuntosAtencion;
    }
    public void addPuntoAtencion(PuntosAtencion puntoAtencion) {
        this.listaPuntosAtencion.add(puntoAtencion);
    }
    public void removePuntoAtencion(PuntosAtencion puntoAtencion) {
        this.listaPuntosAtencion.remove(puntoAtencion);
    }
    public ArrayList<PersonalBiblioteca> getListaPersonal() {
        return listaPersonal;
    }
    public void addPersonal(PersonalBiblioteca personal) {
        this.listaPersonal.add(personal);
    }
    public void removePersonal(PersonalBiblioteca personal) {
        this.listaPersonal.remove(personal);
    }
    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
}
