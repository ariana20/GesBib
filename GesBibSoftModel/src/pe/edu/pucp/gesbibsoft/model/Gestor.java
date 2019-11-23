package pe.edu.pucp.gesbibsoft.model;



import java.util.ArrayList;
import java.util.Date;

public class Gestor extends Personal {

    //ATRIBUTOS ASOCIACIONES
    private Biblioteca biblioteca;
    private ArrayList<Aviso> avisos;

    //CONSTRUCTORES
    public Gestor() {
        super();
        //this.biblioteca=new Biblioteca();
    }


    public Gestor(String nombre, String apellido, String codigo, String email, String contrasenia, Date fechaIngreso, float totalHorasExtra) {
        super(nombre, apellido, codigo, email, contrasenia, fechaIngreso, totalHorasExtra);
        //this.biblioteca=new Biblioteca();
        
    }
    //GETTERS Y SETTERS
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    public ArrayList<Aviso> getAvisos() {
        return this.avisos;
    }
    public void setAvisos(ArrayList<Aviso> avisos) {
        this.avisos = avisos;
    }


    // METODOS ADICIONALES
    public void distribuirPersonal(){}
    public void actualizarFuncionesPersonal(Personal personal){}
    public void actualizarPersonalDisponible(Personal persona, int opt){}
    public void modificarDistribucionPersonal(Personal persona){}
    public boolean validarSolicitudHorasLibres (Personal personal){ return true;}
    public boolean validarCambioTurno (Personal personal,Personal personal2){ return true;}
    public boolean validarJustificacion (Personal personal){ return true;}
    
    public void emitirReporteInasistencia(){} //reporte general de inasistencias
    public void emitirReportePuntosAtencion(){}
    public void colocarAvisos(){} //El gestor puede colocar avisos en la interfaz Principal
    
}
