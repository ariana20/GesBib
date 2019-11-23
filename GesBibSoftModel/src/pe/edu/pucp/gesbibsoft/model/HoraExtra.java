package pe.edu.pucp.gesbibsoft.model;



import java.sql.Time;
import java.util.Date;

public class HoraExtra{

    // ATRIBUTOS PROPIOS
    private int id;
    private Date fecha;
    private String descripcion;
    private float cantidadHoras;
    private Time horaInicio;
    private Time horaFin;
    private int justificado;
    
    // ATRIBUTOS ASOCIACIONES
    private Personal personal;
    
    // CONSTRUCTOR
    public HoraExtra(Date fecha, String descripcion, float cantidadHoras, Time horaInicio, Time horaFin) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cantidadHoras = cantidadHoras;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        //actualizar el atributo del Personal asociado
        this.personal.actualizarHorasExtra(cantidadHoras); 
    }

    public HoraExtra() {
    }



    // SETTERS Y GETTERS
    public void setJustificado(int justificado) {
        this.justificado = justificado;
    }
    
    public int getJustificado() {
        return justificado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(float cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
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
    


}
