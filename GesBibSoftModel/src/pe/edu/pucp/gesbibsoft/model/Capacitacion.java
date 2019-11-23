package pe.edu.pucp.gesbibsoft.model;


import java.util.*;

public class Capacitacion {
    // ATRIBUTOS PROPIOS
    private int id;
    private String nombre;
    private String lugar;
    private String descripcion;
    private Date fecha_ini;
    private Date fecha_fin;
    private Date inicio_inscripcion;
    private Date fin_inscripcion;
    
    
    // ATRIBUTOS ASOCIACIONES
    private ArrayList<Personal> listaPersonal;
    private ArrayList<Dia_Capacitacion> listaDiasCapacitacion;
    
    //SETS AND GETS
    public void setListaPersonal(ArrayList<Personal> listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    public void setListaDiasCapacitacion(ArrayList<Dia_Capacitacion> listaDiasCapacitacion) {    
        this.listaDiasCapacitacion = listaDiasCapacitacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getInicio_inscripcion() {
        return inicio_inscripcion;
    }

    public void setInicio_inscripcion(Date inicio_inscripcion) {
        this.inicio_inscripcion = inicio_inscripcion;
    }

    public Date getFin_inscripcion() {
        return fin_inscripcion;
    }

    public void setFin_inscripcion(Date fin_inscripcion) {
        this.fin_inscripcion = fin_inscripcion;
    }

    public ArrayList<Personal> getListaPersonal() {
        return listaPersonal;
    }
    public void addPersonal(Personal personal) {
        this.listaPersonal.add(personal);
    }
    public void removePersonal(Personal personal) {
        this.listaPersonal.remove(personal);
    }
    public ArrayList<Dia_Capacitacion> getListaDiasCapacitacion() {
        return listaDiasCapacitacion;
    }
    public void addDiaCapacitacion(Dia_Capacitacion dia_capa) {
        this.listaDiasCapacitacion.add(dia_capa);
    }
    public void removeDiaCapacitacion(Dia_Capacitacion dia_capa) {
        this.listaDiasCapacitacion.remove(dia_capa);
    }
    
    
    //CONSTRUCTORES
    public Capacitacion() {
    }

    public Capacitacion(String nombre, String lugar, String descripcion, Date fecha_ini, Date fecha_fin, Date inicio_inscripcion, Date fin_inscripcion) {
       
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.inicio_inscripcion = inicio_inscripcion;
        this.fin_inscripcion = fin_inscripcion;
        this.listaPersonal = new ArrayList<>();
        this.listaDiasCapacitacion = new ArrayList<>();
    }
    
    
    
}
