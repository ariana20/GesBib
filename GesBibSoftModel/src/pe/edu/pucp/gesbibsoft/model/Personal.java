package pe.edu.pucp.gesbibsoft.model;


import java.util.ArrayList;
import java.util.Date;

public class Personal extends Usuario{
    
    //ATRIBUTOS PROPIOS
    private Date fecha_ingreso;
    private byte[] foto;
    private String codigo;
    private float totalHorasExtra; // atributo extra para llevar la cuenta de las horas extra
    
    //ATRIBUTOS DE ASOCIACIONES
    private Turno turno;
    private ArrayList<Capacitacion> listaCapacitaciones;
    private ArrayList<Vacaciones> listaVacaciones;
    private ArrayList<Inasistencia> listaInasistencias;
    private ArrayList<HoraExtra> listaHorasExtra;
    private ArrayList<Personal> cubreRefrigerioA;
    private Personal meCubre;
    private ArrayList<DistribucionPersonal> distribucion; 
    
    
    //CONSTRUCTORES
    public Personal() {
        this.listaCapacitaciones = new ArrayList<>();
        this.listaInasistencias = new ArrayList<>();
        this.listaHorasExtra = new ArrayList<>();
        this.listaVacaciones = new ArrayList<>();
        this.distribucion = new ArrayList<>();
        this.cubreRefrigerioA = new ArrayList<>();
        this.meCubre = null;
        this.fecha_ingreso = null;
        this.totalHorasExtra = 0;
        this.turno = new Turno();
    }
    
    public Personal(String nombre, String apellido, String codigo, String email, String contrasenia, Date fechaIngreso, float totalHorasExtra) {
        super(nombre, apellido,  email, contrasenia);
        this.listaCapacitaciones = new ArrayList<>();
        this.listaInasistencias = new ArrayList<>();
        this.listaHorasExtra = new ArrayList<>();
        this.listaVacaciones = new ArrayList<>();
        this.distribucion = new ArrayList<>();
        this.cubreRefrigerioA = new ArrayList<>();
        this.codigo=codigo;
        this.meCubre = null;
        this.fecha_ingreso = fechaIngreso;
        this.totalHorasExtra = totalHorasExtra;
        this.turno = new Turno();
    }

    public Personal(Date fecha_ingreso, byte[] foto, String codigo, float totalHorasExtra, String nombre, String apellido, String email, String contrasenia) {
        super(nombre, apellido, email, contrasenia);
        this.listaCapacitaciones = new ArrayList<>();
        this.listaInasistencias = new ArrayList<>();
        this.listaHorasExtra = new ArrayList<>();
        this.listaVacaciones = new ArrayList<>();
        this.distribucion = new ArrayList<>();
        this.cubreRefrigerioA = new ArrayList<>();
        this.fecha_ingreso = fecha_ingreso;
        this.foto = foto;
        this.codigo = codigo;
        this.totalHorasExtra = totalHorasExtra;
    }
    
    


    // GETTER Y SETTER
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }
    public ArrayList<Capacitacion> getListaCapacitaciones() {
        return listaCapacitaciones;
    }
    public void setListaCapacitaciones(ArrayList<Capacitacion> listaCapacitaciones) {
        this.listaCapacitaciones = listaCapacitaciones;
    }
    public ArrayList<Vacaciones> getListaVacaciones() {
        return listaVacaciones;
    }
    public void setListaVacaciones(ArrayList<Vacaciones> listaVacaciones) {
        this.listaVacaciones = listaVacaciones;
    }
    public ArrayList<Inasistencia> getListaInasistencias() {
        return listaInasistencias;
    }
    public void setListaInasistencias(ArrayList<Inasistencia> listaInasistencias) {
        this.listaInasistencias = listaInasistencias;
    }
    public ArrayList<HoraExtra> getListaHorasExtra() {
        return listaHorasExtra;
    }
    public void setListaHorasExtra(ArrayList<HoraExtra> listaHorasExtra) {
        this.listaHorasExtra = listaHorasExtra;
    }
    public float getTotalHorasExtra() {
        return totalHorasExtra;
    }
    public void setTotalHorasExtra(float totalHorasExtra) {
        this.totalHorasExtra = totalHorasExtra;
    }
    public ArrayList<Personal> getCubreRefrigerioA() {
        return cubreRefrigerioA;
    }
    public void setCubreRefrigerioA(ArrayList<Personal> cubreRefrigerioA) {
        this.cubreRefrigerioA = cubreRefrigerioA;
    }
    public Personal getMeCubre() {
        return meCubre;
    }
    public void setMeCubre(Personal meCubre) {
        this.meCubre = meCubre;
    }
    public ArrayList<DistribucionPersonal> getDistribucion() {
        return distribucion;
    }
    public void setDistribucion(ArrayList<DistribucionPersonal> distribucion) {
        this.distribucion = distribucion;
    }
    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    // ADD & REMOVE FROM ARRAYLISTS
    public void addCapacitacion(Capacitacion capa) {
        this.getListaCapacitaciones().add(capa);
    }
    public void removeCapacitacion(Capacitacion capa) {
        this.getListaCapacitaciones().remove(capa);
    }
    public void addInasistencia(Inasistencia inasistencia){
        this.getListaInasistencias().add(inasistencia);
    }
    public void removeInasistencia(Inasistencia inasistencia){
        this.getListaInasistencias().remove(inasistencia);
    }
    public void addHorasExtra(HoraExtra horasExtra){
        this.getListaHorasExtra().add(horasExtra);
        this.actualizarHorasExtra(horasExtra.getCantidadHoras());
    }
    public void actualizarHorasExtra(float newExtra){
        this.setTotalHorasExtra(this.getTotalHorasExtra() + newExtra);
    }
    public void removeHorasExtra(HoraExtra horasExtra){
        this.getListaHorasExtra().remove(horasExtra);
        // el actualizarHorasExtra solo suma, tengo que pasarle negativo para que remueva esas horas extra
        this.actualizarHorasExtra(-1*horasExtra.getCantidadHoras()); 
    }
    public void addVacaciones(Vacaciones vacas) {
        this.getListaVacaciones().add(vacas);
    }
    public void removeVacaciones(Vacaciones vacas) {
        this.getListaVacaciones().remove(vacas);
    }    
    public void addCubrirRefrigerio(Personal personal) {
        this.getCubreRefrigerioA().add(personal);
    }
    public void removeCubrirRefrigerio(Personal personal) {
        this.getCubreRefrigerioA().remove(personal);
    }
    public void addDistribucion(DistribucionPersonal distribucionP) {
        this.getDistribucion().add(distribucionP);
    }
    public void removeDistribucion(DistribucionPersonal distribucionP) {
        this.getDistribucion().remove(distribucionP);
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    // METODOS ADICIONALES
    public void cambioHorario(Personal personal){
    }
    public void solicitarHorasLibres(Date fecha){
    }
    public void emitirReporteInasistencias(String mes){};
    public void confirmarAsistenciaCapacitacion(String nombre){};
    public void cancelarInscripcionCapacitacion(String nombre){};
    public void calcularHorasTrabajadas(){};

    
}
