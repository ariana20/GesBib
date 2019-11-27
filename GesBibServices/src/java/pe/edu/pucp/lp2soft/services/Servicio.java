/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lp2soft.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.gesbibsoft.config.DBController;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Auxiliar;
import pe.edu.pucp.gesbibsoft.model.Aviso;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Bibliotecario;
import pe.edu.pucp.gesbibsoft.model.Capacitacion;
import pe.edu.pucp.gesbibsoft.model.Dia_Capacitacion;
import pe.edu.pucp.gesbibsoft.model.DistribucionPersonal;
import pe.edu.pucp.gesbibsoft.model.Gestor;
import pe.edu.pucp.gesbibsoft.model.HoraExtra;
import pe.edu.pucp.gesbibsoft.model.Inasistencia;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.Personal;
import pe.edu.pucp.gesbibsoft.model.PersonalBiblioteca;
import pe.edu.pucp.gesbibsoft.model.Practicante;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;
import pe.edu.pucp.gesbibsoft.model.TipoInasistencia;
import pe.edu.pucp.gesbibsoft.pair.PairStringUsuario;

@WebService(serviceName = "Servicio")
public class Servicio {

    public Servicio() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " ! HOLA";
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //USUARIO
    @WebMethod(operationName = "validarUsuario")
    public PairStringUsuario validarUsuario(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        return DBController.validarUsuario(email, password);
    }

    @WebMethod(operationName = "hallarTipoUsuario")
    public String hallarTipoUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        return DBController.hallarTipoUsuario(idUsuario);
    }

    @WebMethod(operationName = "listarUsuariosLibres")
    public ArrayList<Personal> listarUsuariosLibres(@WebParam(name = "fecha") Date fecha,
            @WebParam(name = "hora_inicio") String hora_inicio,
            @WebParam(name = "hora_fin") String hora_fin,
            @WebParam(name = "nombre_perfil") String nombre_perfil) throws ParseException {
        return DBController.listarUsuariosLibres(fecha, hora_inicio, hora_fin, nombre_perfil);
    }


    @WebMethod(operationName = "actualizarDatosUsuario")
    public int actualizarDatosUsuario(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "foto") byte[] foto) {
        return DBController.actualizarDatosUsuario(id, nombre,apellido,correo,foto);
    }


    
    @WebMethod(operationName = "listarPersonalAsignadoAPerfilExperiencia")
    public ArrayList<PersonalBiblioteca> listarPersonalAsignadoAPerfilExperiencia(@WebParam(name = "idPerfilExperiencia") int idPerfilExperiencia){
        return DBController.listarPersonalPorPerfilExperiencia(idPerfilExperiencia);
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    //PERFIL_EXPERIENCIA
    @WebMethod(operationName = "insertarAviso")
    public void insertarAviso(@WebParam(name = "aviso") Aviso aviso) {
        DBController.insertarAviso(aviso);
    }

    @WebMethod(operationName = "actualizarAviso")
    public void actualizarAviso(@WebParam(name = "aviso") Aviso aviso) {
        DBController.actualizarAviso(aviso);
    }

    @WebMethod(operationName = "elimAviso")
    public void elimAviso(@WebParam(name = "aviso") int idAvisos) {
        DBController.elimAviso(idAvisos);
    }

    @WebMethod(operationName = "listarAvisos")
    public ArrayList<Aviso> listarAvisos() {
        return DBController.listarAvisos();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //PERFIL_EXPERIENCIA
    @WebMethod(operationName = "insertarPerfilExperiencia")
    public int insertarPerfilExperiencia(@WebParam(name = "perfilExperiencia") PerfilExperiencia perfilExperiencia) {
        return DBController.insertarPerfilExperiencia(perfilExperiencia);
    }

    
    @WebMethod(operationName = "actualizarPerfilExperiencia")
    public void actualizarPerfilExperiencia(@WebParam(name = "perfilExperiencia") PerfilExperiencia perfilExperiencia){
        DBController.actualizarPerfilExperiencia(perfilExperiencia);
    }
    
    @WebMethod(operationName = "eliminarPerfilExperiencia")
    public void eliminarPerfilExperiencia(@WebParam(name = "idPerfilExperiencia") int idPerfilExperiencia){
        DBController.elimPerfilExperiencia(idPerfilExperiencia);
    }
    

    @WebMethod(operationName = "listarPerfilExperiencia")
    public ArrayList<PerfilExperiencia> listarPerfilExperiencia() {
        return DBController.listarPerfilExperiencia();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //CAPACITACIONES
    @WebMethod(operationName = "listarCapacitaciones")
    public ArrayList<Capacitacion> listarCapacitaciones() {
        return DBController.listarCapacitacion();
    }

    @WebMethod(operationName = "listarCapacitacionesPersonalxEstado")
    public ArrayList<Capacitacion> listarCapacitacionesPersonalxEstado(
            @WebParam(name = "idPersonal") int idPersonal,
            @WebParam(name = "estado") int estado) {
        return DBController.listarCapaPersxEstado(idPersonal, estado);
    }

    @WebMethod(operationName = "actualizarCapacitacion")
    public int actualizarCapacitacion(@WebParam(name = "capacitacion") Capacitacion capacitacion) {
        return DBController.actualizarCapacitacion(capacitacion);
    }

    //bytys
    @WebMethod(operationName = "actualizarEstadoCapacitacionDePersonal")
    public int actualizarEstadoCapacitacionDePersonal(
            @WebParam(name = "idCapacitacion") int idCapacitacion,
            @WebParam(name = "idPersonal") int idPersonal,
            @WebParam(name = "estado") int estado) {
        return DBController.actualizaEstadoCapacitacionDePersonal(idCapacitacion, idPersonal, estado);
    }

    @WebMethod(operationName = "eliminarCapacitacion")
    public int eliminarCapacitacion(@WebParam(name = "idCapacitacion") int idCapacitacion) {
        return DBController.elimCapacitacion(idCapacitacion);
    }

    @WebMethod(operationName = "insertarCapacitacion")
    public int insertarCapacitacion(@WebParam(name = "capacitacion") Capacitacion capacitacion) {
        return DBController.insertarCapacitacion(capacitacion);
    }

    //DIA_CAPACITACION
    @WebMethod(operationName = "eliminarDiaCapacitacion")
    public int eliminarDiaCapacitacion(@WebParam(name = "idCapacitacion") int idCapacitacion) {
        return DBController.elimDia_Capacitacion(idCapacitacion);
    }

    @WebMethod(operationName = "actualizarDiaCapacitacion")
    public int actualizarDiaCapacitacion(@WebParam(name = "DiaCapacitacion") Dia_Capacitacion diaCapacitacion) {
        return DBController.actualizarDia_Capacitacion(diaCapacitacion);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //BIBLIOTECAS
    @WebMethod(operationName = "insertarBiblioteca")
    public int insertarBiblioteca(@WebParam(name = "biblioteca") Biblioteca biblioteca) {
        return DBController.insertarBiblioteca(biblioteca);
    }

    @WebMethod(operationName = "actualizarBiblioteca")
    public int actualizarBiblioteca(@WebParam(name = "biblioteca") Biblioteca biblioteca) {
        return DBController.actualizarBiblioteca(biblioteca);
    }

    @WebMethod(operationName = "eliminarBiblioteca")
    public int eliminarBiblioteca(@WebParam(name = "biblioteca") int idBiblioteca) {
        return DBController.elimBiblioteca(idBiblioteca);
    }

    @WebMethod(operationName = "listarBibliotecas")
    public ArrayList<Biblioteca> listarBibliotecas() {
        return DBController.listarBiblioteca();
    }

    @WebMethod(operationName = "listarBibliotecasPorNombre")
    public ArrayList<Biblioteca> listarBibliotecasPorNombre(@WebParam(name = "nombre") String nombre) {
        return DBController.listarBibliotecaPorNombre(nombre);
    }

    //PUNTOS ATENCION
    @WebMethod(operationName = "insertarPuntoAtencion")
    public int insertarPuntoAtencion(@WebParam(name = "PuntoAtencion") PuntosAtencion puntoAtencion) {
        return DBController.insertarPuntosAtencion(puntoAtencion);
    }

    @WebMethod(operationName = "actualizarPuntoAtencion")
    public int actualizarPuntoAtencion(@WebParam(name = "PuntoAtencion") PuntosAtencion puntoAtencion) {
        return DBController.actualizarPuntosAtencion(puntoAtencion);
    }

    @WebMethod(operationName = "listarPuntosAtencion")
    public ArrayList<PuntosAtencion> listarPuntosAtencion(@WebParam(name = "idBiblioteca") int idBiblioteca) {
        return DBController.listarPuntosAtencion(idBiblioteca);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //USUARIOS
    @WebMethod(operationName = "asignarPerfil")
    public int asignarPerfil(@WebParam(name = "personalBiblioteca") PersonalBiblioteca personalBiblioteca, @WebParam(name = "perfilExperiencia") PerfilExperiencia perfilExperiencia) {
        return DBController.asignarPerfil(personalBiblioteca, perfilExperiencia);
    }

    @WebMethod(operationName = "listarPersonalBiblioteca")
    public ArrayList<PersonalBiblioteca> listarPersonalBiblioteca(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        return DBController.listarPersonalBiblioteca(nombre, apellido);
    }
    
    
     @WebMethod(operationName = "listartodosobrepersonal")
    public ArrayList<PersonalBiblioteca> listar_todo_sobre_personal(@WebParam(name = "nombre") String nombre){
        return DBController.listar_todo_sobre_personal(nombre);
    }
    

    @WebMethod(operationName = "eliminarPersonalBiblioteca")
    public int eliminarPersonalBiblioteca(@WebParam(name = "idPersonal") int idPersonal) {
        return DBController.eliminarPersonal(idPersonal);
    }

    @WebMethod(operationName = "enviarToken")
    public int enviarToken(@WebParam(name = "correo") String correo) {
        return DBController.enviarToken(correo);
    }

    @WebMethod(operationName = "cambiarContrasenaToken")
    public int cambiarContrasenaToken(@WebParam(name = "correo") String correo, @WebParam(name = "nuevaContrasena") String nuevaContrasena,
            @WebParam(name = "token") String token) {
        return DBController.cambiarContrasenaToken(correo, nuevaContrasena, token);
    }

    
    @WebMethod(operationName = "cambiarContrasenaConfig")
    public int cambiarContrasenaConfig(@WebParam(name = "id") int id, @WebParam(name = "nuevaContrasena") String nuevaContrasena) {
        return DBController.cambiarContrasenaConfig(id, nuevaContrasena);
    }
    
    @WebMethod(operationName = "informacionPersonalBiblioteca")
    public PersonalBiblioteca informacionPersonalBiblioteca(@WebParam(name = "correo") String correo) {
        return DBController.informacionPersonalBiblioteca(correo);
    }
    
    //BIBLIOTECARIO
    @WebMethod(operationName = "insertarBibliotecario")
    public int insertarBibliotecario(@WebParam(name = "bibliotecario") Bibliotecario bibliotecario) {
        return DBController.insertarBibliotecario(bibliotecario);
    }

    @WebMethod(operationName = "actualizarBibliotecario")
    public int actualizarBibliotecario(@WebParam(name = "bibliotecario") Bibliotecario bibliotecario) {
        return DBController.actualizarBibliotecario(bibliotecario, TipoPersonal.Bibliotecario);
    }

    @WebMethod(operationName = "listarBibliotecarios")
    public ArrayList<Bibliotecario> listarBibliotecarios(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        return DBController.listarBibliotecario(nombre, apellido);
    }

    //AUXILIAR BIBLIOTECA
    @WebMethod(operationName = "insertarAuxiliarBiblioteca")
    public int insertarAuxiliarBiblioteca(@WebParam(name = "auxiliar") Auxiliar auxiliar) {
        return DBController.insertarAuxiliar(auxiliar);
    }

    @WebMethod(operationName = "actualizarAuxiliarBiblioteca")
    public int actualizarAuxiliarBiblioteca(@WebParam(name = "bibliotecario") Auxiliar auxiliar) {
        return DBController.actualizarAuxiliar(auxiliar, TipoPersonal.Auxiliar);
    }

    @WebMethod(operationName = "listarAuxiliares")
    public ArrayList<Auxiliar> listarAuxiliares(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        return DBController.listarAuxiliar(nombre, apellido);
    }

    //PRACTICANTE
    @WebMethod(operationName = "insertarPracticante")
    public int insertarPracticante(@WebParam(name = "practicante") Practicante practicante) {
        return DBController.insertarPracticante(practicante);
    }

    @WebMethod(operationName = "actualizarPracticante")
    public int actualizarPracticante(@WebParam(name = "practicante") Practicante practicante) {
        return DBController.actualizarPracticante(practicante, TipoPersonal.Practicante);
    }

    @WebMethod(operationName = "listarPracticantes")
    public ArrayList<Practicante> listarPracticantes(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        return DBController.listarPracticante(nombre, apellido);
    }

    //GESTOR
    @WebMethod(operationName = "insertarGestor")
    public int insertarGestor(@WebParam(name = "gestor") Gestor gestor) {
        return DBController.insertarGestor(gestor);
    }

    @WebMethod(operationName = "actualizarGestor")
    public int actualizarGestor(@WebParam(name = "gestor") Gestor gestor) {
        return DBController.actualizarGestor(gestor);
    }

    @WebMethod(operationName = "eliminarGestor")
    public int eliminarGestor(@WebParam(name = "gestor") int idGestor) {
        return DBController.elimGestor(idGestor);
    }

    @WebMethod(operationName = "listarGestores")
    public ArrayList<Gestor> listarGestores(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        return DBController.listarGestor(nombre, apellido);
    }

    @WebMethod(operationName = "getBibliotecaGestor")
    public Biblioteca getBibliotecaGestor(@WebParam(name = "gestor") int idGestor){
        return DBController.getBibliotecaGestor(idGestor);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////
    //SOLICITUDES
    //HORA EXTRA
    @WebMethod(operationName = "insertarHoraExtra")
    public int insertarHoraExtra(@WebParam(name = "horaExtra") HoraExtra horaExtra) {
        return DBController.insertarHoraExtra(horaExtra);
    }

    @WebMethod(operationName = "actualizarHoraExtra")
    public int actualizarHoraExtra(@WebParam(name = "horaExtra") HoraExtra horaExtra) {
        return DBController.actualizarHoraExtra(horaExtra);
    }

    @WebMethod(operationName = "listarHorasExtra")
    public ArrayList<HoraExtra> listarHorasExtra(@WebParam(name = "idPersonal") int idPersonal) {
        return DBController.listarHoraExtra(idPersonal);
    }

    @WebMethod(operationName = "listarHorasExtraPorFecha")
    public ArrayList<HoraExtra> listarHorasExtraPorFecha(
            @WebParam(name = "idPersonal") int idPersonal, @WebParam(name = "fecha") Date fecha) {
        return DBController.listarHoraExtraPorFecha(idPersonal, fecha);
    }

    @WebMethod(operationName = "eliminarHoraExtra")
    public void eliminarHoraExtra(@WebParam(name = "idHoraExtra") int idHoraExtra) {
        DBController.elimHoraExtra(idHoraExtra);
    }

    //INASISTENCIA
    @WebMethod(operationName = "insertarInasistencia")
    public int insertarInasistencia(@WebParam(name = "inasistencia") Inasistencia inasistencia) {
        return DBController.insertarInasistencia(inasistencia);
    }

    @WebMethod(operationName = "actualizarInasistencia")
    public int actualizarInasistencia(@WebParam(name = "inasistencia") Inasistencia inasistencia) {
        return DBController.actualizarInasistencia(inasistencia);
    }

    //TIPO INASISTENCIA
    @WebMethod(operationName = "listarTipoInasistencia")
    public ArrayList<TipoInasistencia> listarTipoInasistencia() {
        return DBController.listarTipoInasistencia();
    }

    @WebMethod(operationName = "listarInasistencias")
    public ArrayList<Inasistencia> listarInasistencias(@WebParam(name = "idPersonal") int idPersonal) {
        return DBController.listarInasistencia(idPersonal);
    }

    @WebMethod(operationName = "listarInasistenciasPorFecha")
    public ArrayList<Inasistencia> listarInasistenciasPorFecha(
            @WebParam(name = "idPersonal") int idPersonal, @WebParam(name = "fecha") Date fecha) {
        return DBController.listarInasistenciasPorFecha(idPersonal, fecha);
    }

    //HORA LIBRE
    @WebMethod(operationName = "listarHorasLibre")
    public ArrayList<Inasistencia> listarHorasLibre(@WebParam(name = "idPersonal") int idPersonal, @WebParam(name = "estado") int estado) {
        return DBController.listarHorasLibresDePersonal(idPersonal, estado);
    }

    @WebMethod(operationName = "listarTodasHorasLibre")
    public ArrayList<Inasistencia> listarTodasHorasLibre(@WebParam(name = "idPersonal") int idPersonal) {
        return DBController.listarTodasHorasLibresDePersonal(idPersonal);
    }

    @WebMethod(operationName = "listarHorasLibrePorFecha")
    public ArrayList<Inasistencia> listarHorasLibrePorFecha(@WebParam(name = "idPersonal") int idPersonal,
            @WebParam(name = "estado") int estado,
            @WebParam(name = "fecha") Date fecha) {
        return DBController.listarHorasLibresDePersonalPorFecha(idPersonal, estado, fecha);
    }

    @WebMethod(operationName = "eliminarInasistencia")
    public void eliminarHoraLibre(@WebParam(name = "idInasis") int idInasis) {
        DBController.elimInasistencia(idInasis);
    }

    // DISTRIBUCIONPERSONAL

    @WebMethod(operationName = "listarDistribucionPersonalPorFecha")
    public ArrayList<DistribucionPersonal> listarDistribucionPersonalPorFecha(@WebParam(name = "idPersonal") int idPersonal, @WebParam(name = "idPuntoAtencion")  int idPuntoAtencion,
            @WebParam(name = "fechaIni") Date fechaIni, @WebParam(name = "fechaFin") Date fechaFin) throws ParseException{
        return DBController.listarDistribucionPersonalPorFecha( idPuntoAtencion, fechaIni, fechaFin);
    }

//    @WebMethod(operationName = "listarDistribucionPersonalPorFechaHora")
//    public ArrayList<PuntosAtencion> listarDistribucionPersonalPorFechaHora(@WebParam(name = "idPersonal") int idPersonal, @WebParam(name = "idPuntoAtencion") int idPuntoAtencion,
//            @WebParam(name = "fecha") Date fecha, @WebParam(name = "horaIni") String horaIni, @WebParam(name = "horaFin") String horaFin) throws ParseException {
//        return DBController.listarDistribucionPersonalPorFechaHora(idPersonal, idPuntoAtencion, fecha, horaIni, horaFin);
//    }
    
    

    @WebMethod(operationName = "elimDistribucionPersonal")
    public void elimDistribucionPersonal(@WebParam(name = "idDistrib_Per") int idDistrib_Per, @WebParam(name = "idPuntoAtencion") int idPuntoAtencion) {
        DBController.elimDistribucionPersonal(idDistrib_Per, idPuntoAtencion);
    }

    @WebMethod(operationName = "insertarDistribucionPersonal")
    public void insertarDistribucionPersonal(@WebParam(name = "distrib_Per") DistribucionPersonal distrib_Per) {
        DBController.insertarDistribucionPersonal(distrib_Per);
    }
    

    
    // REPORTEEEEEES AEEEEA
    @WebMethod(operationName = "generarReporteInasistencias")
    public byte[] generarReporteInasistencias( @WebParam (name = "ID_USUARIO") int idUsuario , 
                                     @WebParam (name = "FECHA_INICIO") Date fechaInicio,
                                     @WebParam (name = "FECHA_FIN") Date fechaFin){
        byte[] arreglo = null;
        try{ 
            String rutaPrincipal = Servicio.class.getResource("/pe/edu/pucp/lp2soft/reports/Reporte_Inasistencias_1.jasper").getPath(); //cambiar por mi ruta
            rutaPrincipal = rutaPrincipal.replaceAll("%20", " ");
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaPrincipal);
            
            Class.forName("com.mysql.cj.jdbc.Driver");//cambiar a mysql
            Connection con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
           
            HashMap hm = new HashMap(); 
            //cambiar datos de entrada o parámetros
            hm.put("ID_USUARIO", idUsuario);
            hm.put("FECHA_INICIO",fechaInicio);
            hm.put("FECHA_FIN",fechaFin);
            
            JasperPrint jp = JasperFillManager.fillReport(reporte,hm,con);                  
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
	}
	return arreglo;
    }
    
    
    @WebMethod(operationName = "generarReporteHorasTrabajadas")
    public byte[] generarReporteHorasTrabajadas( @WebParam (name = "ID_PERSONAL") int idPersonal, 
                                     @WebParam (name = "FECHA_INICIO") Date fechaInicio,
                                     @WebParam (name = "FECHA_FIN") Date fechaFin){
        byte[] arreglo = null;
        try{ 
            String rutaPrincipal = Servicio.class.getResource("/pe/edu/pucp/lp2soft/reports/Horas_Trabajadas.jasper").getPath(); //cambiar por mi ruta
            rutaPrincipal = rutaPrincipal.replaceAll("%20", " ");
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaPrincipal);
            
            Class.forName("com.mysql.cj.jdbc.Driver");//cambiar a mysql
            Connection con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
           
            HashMap hm = new HashMap(); 
            //cambiar datos de entrada o parámetros
            hm.put("ID_PERSONAL", idPersonal);
            hm.put("FECHA_INICIO",fechaInicio);
            hm.put("FECHA_FIN",fechaFin);
            
            JasperPrint jp = JasperFillManager.fillReport(reporte,hm,con);                  
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
	}
	return arreglo;
    }

    //bytys
     @WebMethod(operationName = "listarDistribPersonalenFecha")
    public ArrayList<DistribucionPersonal> listarDistribPersonalenFecha(
            @WebParam(name = "id_Personal") int id_Personal,
            @WebParam(name = "fecha") Date fecha ) {
        return DBController.listarDistribPersonalenFecha(id_Personal,fecha);
    }    
    //bytys
    
    
    

}
