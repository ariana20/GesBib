package pe.edu.pucp.gesbibsoft.model;

import java.util.Date;



public class Vacaciones {
    // ATRIBUTOS PROPIOS
    private int id;
    private Date fecha_inicio;
    private Date fecha_fin;
    
    // ATRIBUTOS ASOSIACIONES
    private Personal personal;
    
    public int getId() {
        return id;
    }

    // GETTERS Y SETTERS
    public void setId(int id) {    
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Date getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public Personal getPersonal() {
        return personal;
    }
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
