
package pe.edu.pucp.gesbibsoft.model;

import java.sql.Time;
import pe.edu.pucp.gesbibsoft.enums.Mes;

import java.util.ArrayList;

public class Turno{
    // ATRIBUTOS PROPIOS
    private int id;
    private String semestre;
    private Time horaInicio;
    private Time horaFin;
    private Time refrigerioInicio;
    private Time refrigerioFin;
 // ATRIBUTOS ASOCIACIONES
    private ArrayList<Personal> listaPersonal;
    
    // CONSTRUCTOR
    public Turno(String semestre, Time horaInicio, Time horaFin, Time refrigerioInicio, Time refrigerioFin) {
        this.semestre = semestre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.refrigerioInicio = refrigerioInicio;
        this.refrigerioFin = refrigerioFin;
        listaPersonal=new ArrayList<>();
        
    }
    public Turno() {
        listaPersonal=new ArrayList<>();
        
    }
    // GETTERS Y SETTERS
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }



    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getRefrigerioInicio() {
        return refrigerioInicio;
    }

    public void setRefrigerioInicio(Time refrigerioInicio) {
        this.refrigerioInicio = refrigerioInicio;
    }

    public Time getRefrigerioFin() {
        return refrigerioFin;
    }

    public void setRefrigerioFin(Time refrigerioFin) {
        this.refrigerioFin = refrigerioFin;
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



    
}