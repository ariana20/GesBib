package pe.edu.pucp.gesbibsoft.model;

import java.sql.Time;
import java.util.Date;

public class Dia_Capacitacion {
    //ATRIBUTOS PROPIOS
    private int idDiaCapacitacion;
    private Time hora_ini;
    private Time hora_fin;
    private Date fecha;
    private int activo;
    
    // ATRIBUTOS ASOCIACIONES
    private Capacitacion capacitacion;

    // GETTERS Y SETTERS
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    public Capacitacion getCapacitacion() {
        return capacitacion;
    }
    public void setCapacitacion(Capacitacion capacitacion) {
        this.capacitacion = capacitacion;
    }
    public int getIdDiaCapacitacion() {
        return idDiaCapacitacion;
    }
    public void setIdDiaCapacitacion(int idDiaCapacitacion) {
        this.idDiaCapacitacion = idDiaCapacitacion;
    }
    public Time getHora_ini() {
        return hora_ini;
    }
    public void setHora_ini(Time fecha_ini) {
        this.hora_ini = fecha_ini;
    }
    public Time getHora_fin() {
        return hora_fin;
    }
    public void setHora_fin(Time fecha_fin) {
        this.hora_fin = fecha_fin;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    // CONSTRUCTORS
    public Dia_Capacitacion() {
    }

    public Dia_Capacitacion( Time hora_ini, Time hora_fin, Date fecha) {
        
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
        this.fecha = fecha;
    }

    
    
}
