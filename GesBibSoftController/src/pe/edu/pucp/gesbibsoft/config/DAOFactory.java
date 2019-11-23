/**
 * @author Josue Manuel
 */
package pe.edu.pucp.gesbibsoft.config;

import pe.edu.pucp.gesbibsoft.dao.AdministradorSIDAO;
import pe.edu.pucp.gesbibsoft.dao.AuxiliarDAO;
import pe.edu.pucp.gesbibsoft.dao.AvisoDAO;
import pe.edu.pucp.gesbibsoft.dao.BibliotecaDAO;
import pe.edu.pucp.gesbibsoft.dao.BibliotecarioDAO;
import pe.edu.pucp.gesbibsoft.dao.CapacitacionDAO;
import pe.edu.pucp.gesbibsoft.dao.Dia_CapacitacionDAO;
import pe.edu.pucp.gesbibsoft.dao.DistribucionPersonalDAO;
import pe.edu.pucp.gesbibsoft.dao.GestorDAO;
import pe.edu.pucp.gesbibsoft.dao.HoraExtraDAO;
import pe.edu.pucp.gesbibsoft.dao.InasistenciaDAO;
import pe.edu.pucp.gesbibsoft.dao.PerfilExperienciaDAO;
import pe.edu.pucp.gesbibsoft.dao.PersonalBibliotecaDAO;
import pe.edu.pucp.gesbibsoft.dao.PersonalDAO;
import pe.edu.pucp.gesbibsoft.dao.PracticanteDAO;
import pe.edu.pucp.gesbibsoft.dao.PuntosAtencionDAO;
import pe.edu.pucp.gesbibsoft.dao.TipoInasistenciaDAO;
import pe.edu.pucp.gesbibsoft.dao.TurnoDAO;
import pe.edu.pucp.gesbibsoft.dao.UsuarioDAO;
import pe.edu.pucp.gesbibsoft.dao.VacacionesDAO;

public abstract class DAOFactory {

    public abstract Dia_CapacitacionDAO getDia_CapacitacionDAO();
    
    public abstract AdministradorSIDAO getAdministradorSIDAO();

    public abstract AuxiliarDAO getAuxiliarDAO();

    public abstract BibliotecaDAO getBibliotecaDAO();

    public abstract BibliotecarioDAO getBibliotecarioDAO();

    public abstract CapacitacionDAO getCapacitacionDAO();

    public abstract DistribucionPersonalDAO getDistribucionPersonalDAO();

    public abstract GestorDAO getGestorDAO();

    public abstract HoraExtraDAO getHoraExtraDAO();

    public abstract InasistenciaDAO getInasistenciaDAO();
    
    public abstract TipoInasistenciaDAO getTipoInasistenciaDAO();

    public abstract PerfilExperienciaDAO getPerfilExperenciaDAO();

    public abstract PracticanteDAO getPracticanteDAO();

    public abstract PuntosAtencionDAO getPuntosAtencionDAO();

    public abstract TurnoDAO getTurnoDAO();

    public abstract VacacionesDAO getVacacionesDAO();
    
    public abstract AvisoDAO getAvisoDAO();
    
    public abstract UsuarioDAO getUsuarioDAO();
    
    public abstract PersonalDAO getPersonalDAO();
      
    public abstract PersonalBibliotecaDAO getPersonalBibliotecaDAO();
    
    public static DAOFactory getDAOFactory() {

        return new MySQLDAOFactory();
//        if (DBManager.url.contains("mysql")) {
//            return new MySQLDAOFactory();
//        } else {
//            //return new MSSQLDAOFactory;
//        }
    }


}
