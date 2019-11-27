package pe.edu.pucp.gesbibsoft.config;

import java.util.ArrayList;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.AdministradorSI;
import pe.edu.pucp.gesbibsoft.model.Auxiliar;
import pe.edu.pucp.gesbibsoft.model.Aviso;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Bibliotecario;
import pe.edu.pucp.gesbibsoft.model.Capacitacion;
import pe.edu.pucp.gesbibsoft.model.Dia_Capacitacion;
import pe.edu.pucp.gesbibsoft.model.DistribucionPersonal;
import pe.edu.pucp.gesbibsoft.model.Gestor;
import pe.edu.pucp.gesbibsoft.model.Personal;
import pe.edu.pucp.gesbibsoft.model.HoraExtra;
import pe.edu.pucp.gesbibsoft.model.Inasistencia;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.PersonalBiblioteca;
import pe.edu.pucp.gesbibsoft.model.Practicante;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;
import pe.edu.pucp.gesbibsoft.model.TipoInasistencia;
import pe.edu.pucp.gesbibsoft.model.Turno;
import pe.edu.pucp.gesbibsoft.model.Vacaciones;
import pe.edu.pucp.gesbibsoft.pair.PairStringUsuario;

public abstract class DBController {

    private static final DAOFactory daoFact = DAOFactory.getDAOFactory();

    //ahora vemos la lista de metodos de acceso
    //AdministradorSI
    public static void insertarAdminSI(AdministradorSI adminSI) {
        daoFact.getAdministradorSIDAO().insertar(adminSI);
    }

    public static void actualizarAdminSI(AdministradorSI adminSI) {
        daoFact.getAdministradorSIDAO().actualizar(adminSI);
    }

    public static void elimAdminSI(int idAdminSI) {
        daoFact.getAdministradorSIDAO().eliminar(idAdminSI);
    }

    public static ArrayList<AdministradorSI> listarAdminsSI() {
        return daoFact.getAdministradorSIDAO().listar();
    }
    
    //Dia_Capacitacion
    public static void insertarDia_Capacitacion(Dia_Capacitacion dia_capacitacion) {
        daoFact.getDia_CapacitacionDAO().insertar(dia_capacitacion);
    }

    public static int actualizarDia_Capacitacion(Dia_Capacitacion dia_capacitacion) {
        return daoFact.getDia_CapacitacionDAO().actualizar(dia_capacitacion);
    }

    public static int elimDia_Capacitacion(int idDia_capacitacion) {
        return daoFact.getDia_CapacitacionDAO().eliminar(idDia_capacitacion);
    }

    public static ArrayList<Dia_Capacitacion> listarDia_Capacitacion(int dia_capacitacion) {
        return daoFact.getDia_CapacitacionDAO().listar(dia_capacitacion);
    }

    //Auxiliar
    public static int insertarAuxiliar(Auxiliar auxiliar) {
        return daoFact.getAuxiliarDAO().insertar(auxiliar, TipoPersonal.Auxiliar);
    }

    public static int actualizarAuxiliar(Auxiliar auxiliar, TipoPersonal idTipoPersonal) {
        return daoFact.getAuxiliarDAO().actualizar(auxiliar, idTipoPersonal);
    }

    public static void elimAuxiliar(int idAuxiliar) {
        daoFact.getAuxiliarDAO().eliminar(idAuxiliar);
    }

    public static ArrayList<Auxiliar> listarAuxiliar(String nombre, String apellido) {
        return daoFact.getAuxiliarDAO().listar(nombre, apellido);
    }

    //BIBLIOTECA
    public static int insertarBiblioteca(Biblioteca biblioteca) {
        return daoFact.getBibliotecaDAO().insertar(biblioteca);
    }

    public static int actualizarBiblioteca(Biblioteca Biblioteca) {
        return daoFact.getBibliotecaDAO().actualizar(Biblioteca);
    }

    public static int elimBiblioteca(int idBiblioteca) {
        return daoFact.getBibliotecaDAO().eliminar(idBiblioteca);
    }

    public static ArrayList<Biblioteca> listarBiblioteca() {
        return daoFact.getBibliotecaDAO().listar();
    }
    
    public static ArrayList<Biblioteca> listarBibliotecaPorNombre(String nombre) {
        return daoFact.getBibliotecaDAO().listar_por_nombre(nombre);
    }

    //BIBLIOTECARIO
    public static int insertarBibliotecario(Bibliotecario bibliotecario) {
        return daoFact.getBibliotecarioDAO().insertar(bibliotecario, TipoPersonal.Bibliotecario);
    }

    public static int actualizarBibliotecario(Bibliotecario bibliotecario, TipoPersonal idTipoPersonal) {
        return daoFact.getBibliotecarioDAO().actualizar(bibliotecario, idTipoPersonal);
    }

    public static void elimBibliotecario(int idBibliotecario) {
        daoFact.getBibliotecarioDAO().eliminar(idBibliotecario);
    }

    public static ArrayList<Bibliotecario> listarBibliotecario(String nombre, String apellido) {
        return daoFact.getBibliotecarioDAO().listar( nombre,  apellido);
    }

    //CAPACITACION
    public static int insertarCapacitacion(Capacitacion capacitacion) {
        return daoFact.getCapacitacionDAO().insertar(capacitacion);
    }

    public static int actualizarCapacitacion(Capacitacion capacitacion) {
        return daoFact.getCapacitacionDAO().actualizar(capacitacion);
    }
   
    
    //by tys
    public static int actualizaEstadoCapacitacionDePersonal(int idCapacitacion, int idPersonal, int estado) {
        return daoFact.getCapacitacionDAO().actualizaEstadoCapacitacionDePersonal(idCapacitacion,idPersonal,estado);
    }
        
    

    public static int elimCapacitacion(int idCapacitacion) {
        return daoFact.getCapacitacionDAO().eliminar(idCapacitacion);
    }

    public static ArrayList<Capacitacion> listarCapacitacion() {
        return daoFact.getCapacitacionDAO().listar();
    }
    
    public static ArrayList<Capacitacion> 
        listarCapaPersxEstado(int idPers,int estado) {
        return daoFact.getCapacitacionDAO().listarCapacitacionesDePersonalxEstado(
                idPers, estado);
    }
    
 //listarCapacitacionesDePersonalxEstado

    //DISTRIBUCION_PERSONAL
    public static void insertarDistribucionPersonal(DistribucionPersonal distrib_Per) {
        daoFact.getDistribucionPersonalDAO().insertar(distrib_Per);
    }

    public static void actualizarDistribucionPersonal(DistribucionPersonal distrib_Per) {
        daoFact.getDistribucionPersonalDAO().actualizar(distrib_Per);
    }

    public static void elimDistribucionPersonal(int idDistrib_Per, int idPuntoAtencion) {
        daoFact.getDistribucionPersonalDAO().eliminar(idDistrib_Per, idPuntoAtencion);
    }

    public static ArrayList<DistribucionPersonal> listarDistribucionPersonal(int idPersonal,Date fechaIni, Date fechaFin) {
        return daoFact.getDistribucionPersonalDAO().listarPorPersonal(idPersonal, fechaIni, fechaFin);
    }
//    public static ArrayList<PuntosAtencion> listarDistribucionPersonalPorFechaHora(int idPersonal, int idPuntoAtencion,
//            Date fecha, String horaIni, String horaFin) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        long ms1 = sdf.parse(horaIni).getTime();
//        Time horaI = new Time(ms1);
//        long ms2 = sdf.parse(horaFin).getTime();
//        Time horaF = new Time(ms2);
//        return daoFact.getDistribucionPersonalDAO().listarPorFechaHora(idPuntoAtencion, fecha, horaI, horaF);
//    }
    public static ArrayList<DistribucionPersonal> listarDistribucionPersonalPorFecha(int idPuntoAtencion, Date fechaIni, Date fechaFin) {
        return daoFact.getDistribucionPersonalDAO().listarPorFechaHora(idPuntoAtencion, fechaIni, fechaFin);
    }
    //GESTOR
    public static int insertarGestor(Gestor gestor) {
        return daoFact.getGestorDAO().insertar(gestor);
    }

    public static int actualizarGestor(Gestor gestor) {
        return daoFact.getGestorDAO().actualizar(gestor);
    }

    public static int elimGestor(int idGestor) {
        return daoFact.getGestorDAO().eliminar(idGestor);
    }

    public static ArrayList<Gestor> listarGestor(String nombre, String apellido) {
        return daoFact.getGestorDAO().listar( nombre,  apellido);
    }

    public static Biblioteca getBibliotecaGestor(int idGestor) {
        return daoFact.getGestorDAO().getBiblioteca(idGestor);
    }
        
    //HORA_EXTRA    
    public static int insertarHoraExtra(HoraExtra horaEx) {
        return daoFact.getHoraExtraDAO().insertar(horaEx);
    }

    public static int actualizarHoraExtra(HoraExtra horaEx) {
        return daoFact.getHoraExtraDAO().actualizar(horaEx);
    }

    public static void elimHoraExtra(int idHoraEx) {
        daoFact.getHoraExtraDAO().eliminar(idHoraEx);
    }

    public static ArrayList<HoraExtra> listarHoraExtra(int idPersonal) {
        return daoFact.getHoraExtraDAO().listar(idPersonal);
    }
    
    public static ArrayList<HoraExtra> listarHoraExtraPorFecha(int idPersonal, Date fecha) {
        return daoFact.getHoraExtraDAO().listarPorFecha(idPersonal, fecha);
    }
    
    public static ArrayList<Inasistencia> listarInasistencias(int idPersonal) {
        return daoFact.getInasistenciaDAO().listarInasistencias(idPersonal);
    }
    
    public static ArrayList<Inasistencia> listarInasistenciasPorFecha(int idPersonal, Date fecha) {
        return daoFact.getInasistenciaDAO().listarInasistenciasPorFecha(idPersonal, fecha);
    }

    //INASISTENCIA    
    public static int insertarInasistencia(Inasistencia inasistencia) {
        return daoFact.getInasistenciaDAO().insertar(inasistencia);
    }

    public static int actualizarInasistencia(Inasistencia inasistencia) {
        return daoFact.getInasistenciaDAO().actualizar(inasistencia);
    }

    public static void elimInasistencia(int idInasistencia) {
        daoFact.getInasistenciaDAO().eliminar(idInasistencia);
    }

    public static ArrayList<Inasistencia> listarInasistencia(int idPersonal) {
        return daoFact.getInasistenciaDAO().listarPorPersonal(idPersonal);
    }
    //HORA_LIBRE
    
    public static ArrayList<Inasistencia> listarHorasLibresDePersonal(int idPersonal, int justificado) {
        return daoFact.getInasistenciaDAO().listarHorasLibresDePersonal(idPersonal, justificado);
    }
    public static ArrayList<Inasistencia> listarTodasHorasLibresDePersonal(int idPersonal) {
        return daoFact.getInasistenciaDAO().listarTodasHorasLibresDePersonal(idPersonal);
    }
    public static ArrayList<Inasistencia> listarHorasLibresDePersonalPorFecha(
                                int idPersonal, int justificado, Date fecha) {
        return daoFact.getInasistenciaDAO().listarHorasLibresDePersonalPorFecha(idPersonal, justificado, fecha);
    }
    
    //TIPO_INASITENCIA
    public static void insertarTipoInasistencia(TipoInasistencia tipoInasistencia){
        daoFact.getTipoInasistenciaDAO().insertar(tipoInasistencia);
    }
    
    public static void actualizarTipoInasistencia(TipoInasistencia tipoInasistencia){
        daoFact.getTipoInasistenciaDAO().actualizar(tipoInasistencia);
    }

    public static void elimTipoInasistencia(int idTipoInasistencia) {
        daoFact.getTipoInasistenciaDAO().eliminar(idTipoInasistencia);
    }

    public static ArrayList<TipoInasistencia> listarTipoInasistencia() {
        return daoFact.getTipoInasistenciaDAO().listar();
    }
    
    //PERFIL_EXPERIENCIA
    public static int insertarPerfilExperiencia(PerfilExperiencia perfil_Expe) {
        return daoFact.getPerfilExperenciaDAO().insertar(perfil_Expe);
    }

    public static void actualizarPerfilExperiencia(PerfilExperiencia perfil_Expe) {
        daoFact.getPerfilExperenciaDAO().actualizar(perfil_Expe);
    }

    public static void elimPerfilExperiencia(int idPerfilExp) {
        daoFact.getPerfilExperenciaDAO().eliminar(idPerfilExp);
    }

    public static ArrayList<PerfilExperiencia> listarPerfilExperiencia() {
        return daoFact.getPerfilExperenciaDAO().listar();
    }

    //PRACTICANTE
    public static int insertarPracticante(Practicante practicante) {
        return daoFact.getPracticanteDAO().insertar(practicante, TipoPersonal.Practicante);
    }

    public static int actualizarPracticante(Practicante practicante, TipoPersonal idTipoPersonal) {
        return daoFact.getPracticanteDAO().actualizar(practicante,  idTipoPersonal);
    }

    public static void elimPracticante(int idPracticante) {
        daoFact.getPracticanteDAO().eliminar(idPracticante);
    }

    public static ArrayList<Practicante> listarPracticante(String nombre, String apellido) {
        return daoFact.getPracticanteDAO().listar(nombre, apellido);
    }

    //PUNTOS_ATENCION
    public static int insertarPuntosAtencion(PuntosAtencion puntos_atenc) {
        return daoFact.getPuntosAtencionDAO().insertar(puntos_atenc);
    }

    public static int actualizarPuntosAtencion(PuntosAtencion puntos_atenc) {
        return daoFact.getPuntosAtencionDAO().actualizar(puntos_atenc);
    }

    public static void elimPuntosAtencion(int idPuntos_atenc) {
        daoFact.getPuntosAtencionDAO().eliminar(idPuntos_atenc);
    }

    public static ArrayList<PuntosAtencion> listarPuntosAtencion(int idBiblioteca) {
        return daoFact.getPuntosAtencionDAO().listar(idBiblioteca);
    }

    //TURNO
    public static void insertarTurno(Turno turno) {
        daoFact.getTurnoDAO().insertar(turno);
    }

    public static void actualizarTurno(Turno turno) {
        daoFact.getTurnoDAO().actualizar(turno);
    }

    public static void elimTurno(int idTurno) {
        daoFact.getTurnoDAO().eliminar(idTurno);
    }

    public static ArrayList<Turno> listarTurno() {
        return daoFact.getTurnoDAO().listar();
    }

    //VACACIONES
    public static void insertarVacaciones(Vacaciones vacaciones) {
        daoFact.getVacacionesDAO().insertar(vacaciones);
    }

    public static void actualizarVacaciones(Vacaciones vacaciones) {
        daoFact.getVacacionesDAO().actualizar(vacaciones);
    }

    public static void elimVacaciones(int idVacaciones) {
        daoFact.getVacacionesDAO().eliminar(idVacaciones);
    }

    public static ArrayList<Vacaciones> listarVacaciones() {
        return daoFact.getVacacionesDAO().listar();
    }
    //AVISO
    public static void insertarAviso(Aviso aviso) {
        daoFact.getAvisoDAO().insertar(aviso);
    }

    public static void actualizarAviso(Aviso aviso) {
        daoFact.getAvisoDAO().actualizar(aviso);
    }

    public static void elimAviso(int idAvisos) {
        daoFact.getAvisoDAO().eliminar(idAvisos);
    }

    public static ArrayList<Aviso> listarAvisos() {
        return daoFact.getAvisoDAO().listar();
    }

    public static PairStringUsuario  validarUsuario(String email, String password) {
        return daoFact.getUsuarioDAO().validarUsuario(email, password);
    }
    //PERSONAL_BIBLIOTECA
    public static int asignarPerfil(PersonalBiblioteca personalBiblioteca, PerfilExperiencia perfilExperiencia) {
        return daoFact.getPersonalBibliotecaDAO().asignarPerfil(personalBiblioteca,perfilExperiencia);
       
    }
    public static ArrayList<PersonalBiblioteca> listarPersonalBiblioteca(String nombre, String apellido){
        return daoFact.getPersonalBibliotecaDAO().listar(nombre, apellido);
    }
    
    public static ArrayList<PersonalBiblioteca> listar_todo_sobre_personal(String nombre){
        return daoFact.getPersonalBibliotecaDAO().listar_todo_sobre_personal(nombre);
    }

    public static String hallarTipoUsuario(int idUsuario) {
        return daoFact.getPersonalDAO().hallarTipoUsuario(idUsuario);
    }
    
    public static int enviarToken(String correo) {
        return daoFact.getUsuarioDAO().enviarToken(correo);
    }
    
    public static int cambiarContrasenaToken(String correo, String nuevaContrasena, String token) {
        return daoFact.getUsuarioDAO().cambiarContrasenaToken(correo, nuevaContrasena, token);
    }
    
    public static int cambiarContrasenaConfig(int id, String nuevaContrasena) {
        return daoFact.getUsuarioDAO().cambiarContrasenaConfig(id, nuevaContrasena);
    }
    
    public static int actualizarDatosUsuario(int id,String nombre, String apellido, String correo, byte[] foto){
        return daoFact.getUsuarioDAO().actualizarDatosUsuario(id, nombre,apellido,correo, foto);
    }    
    
    public static PersonalBiblioteca informacionPersonalBiblioteca(String correo) {
        return daoFact.getPersonalBibliotecaDAO().informacionPersonalBiblioteca(correo);
    }
    
    public static int eliminarPersonal(int idPersonal) {
        return daoFact.getPersonalBibliotecaDAO().eliminar(idPersonal);
    }
    
    // Utilitarios
    public static ArrayList<Personal> listarUsuariosLibres(Date fecha, String hora_inicio, String hora_fin, String nombre_perfil) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long ms1 = sdf.parse(hora_inicio).getTime();
        Time horaI = new Time(ms1);
        long ms2 = sdf.parse(hora_fin).getTime();
        Time horaF = new Time(ms2);
        return daoFact.getUsuarioDAO().listarUsuariosLibres(fecha, horaI, horaF, nombre_perfil);
    }

    
    public static ArrayList<PersonalBiblioteca> listarPersonalPorPerfilExperiencia (int idPerfilExperiencia){
        return daoFact.getPersonalBibliotecaDAO().listarPorPerfilExperiencia(idPerfilExperiencia);
    }
    

}
