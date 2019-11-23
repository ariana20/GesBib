package pe.edu.pucp.gesbibsoft.model;



import java.sql.Time;
import java.util.Date;

public class Inasistencia {

    // ATRIBUTOS PROPIOS
    private int id;
    private Date fecha;
    private String motivo;
    private Time horaInicio;
    private Time horaFin;
    private int justificado;
    // ATRIBUTOS ASOCIACIONES
    private Personal personal;
    private TipoInasistencia tipoInasistencia;
    
    //falta tipoInasistencia
    
    // CONSTRUCTORES
    public Inasistencia(Date fecha, String motivo, Time horaInicio, Time horaFin) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Inasistencia(Date fecha, String motivo, Time horaInicio, Time horaFin, Personal personal, TipoInasistencia tipoInasistencia) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.personal = personal;
        this.tipoInasistencia = tipoInasistencia;
    }

    public Inasistencia() {
    }
    

    // SETTERS Y GETTERS

    public int getJustificado() {
        return justificado;
    }

    public void setJustificado(int justificado) {
        this.justificado = justificado;
    }
  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public TipoInasistencia getTipoInasistencia() {
        return tipoInasistencia;
    }

    public void setTipoInasistencia(TipoInasistencia tipoInasistencia) {
        this.tipoInasistencia = tipoInasistencia;
    }


    

    
}
