package pe.edu.pucp.gesbibsoft.model;



import java.sql.Time;
import java.util.Date;

public class DistribucionPersonal {
    // ATRIBUTOS PROPIOS
    
    // ATRIBUTOS ASOCIACIONES
    private Personal personal;
    private PuntosAtencion puntoAtencion;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    
    // CONSTRUCTORES
    public DistribucionPersonal() {
    }

    public DistribucionPersonal(Date fecha, Time horaInicio, Time horaFin) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
   

     //GETTERS Y SETTERS

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public PuntosAtencion getPuntoAtencion() {
        return puntoAtencion;
    }

    public void setPuntoAtencion(PuntosAtencion puntoAtencion) {
        this.puntoAtencion = puntoAtencion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    
    

    
}
