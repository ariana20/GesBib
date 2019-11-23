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
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.mysql.AdministradorSIMySQL;
import pe.edu.pucp.gesbibsoft.mysql.AuxiliarMySQL;
import pe.edu.pucp.gesbibsoft.mysql.AvisoMySQL;
import pe.edu.pucp.gesbibsoft.mysql.BibliotecaMySQL;
import pe.edu.pucp.gesbibsoft.mysql.BibliotecarioMySQL;
import pe.edu.pucp.gesbibsoft.mysql.CapacitacionMySQL;
import pe.edu.pucp.gesbibsoft.mysql.Dia_CapacitacionMySQL;
import pe.edu.pucp.gesbibsoft.mysql.DistribucionPersonalMySQL;
import pe.edu.pucp.gesbibsoft.mysql.GestorMySQL;
import pe.edu.pucp.gesbibsoft.mysql.HoraExtraMySQL;
import pe.edu.pucp.gesbibsoft.mysql.InasistenciaMySQL;
import pe.edu.pucp.gesbibsoft.mysql.PerfilExperienciaMySQL;
import pe.edu.pucp.gesbibsoft.mysql.PersonalBibliotecaMySQL;
import pe.edu.pucp.gesbibsoft.mysql.PersonalMySQL;
import pe.edu.pucp.gesbibsoft.mysql.PracticanteMySQL;
import pe.edu.pucp.gesbibsoft.mysql.PuntosAtencionMySQL;
import pe.edu.pucp.gesbibsoft.mysql.TipoInasistenciaMySQL;
import pe.edu.pucp.gesbibsoft.mysql.TurnoMySQL;
import pe.edu.pucp.gesbibsoft.mysql.UsuarioMySQL;
import pe.edu.pucp.gesbibsoft.mysql.VacacionesMySQL;

public class MySQLDAOFactory extends DAOFactory {

    public MySQLDAOFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Dia_CapacitacionDAO getDia_CapacitacionDAO() {
        return new Dia_CapacitacionMySQL();
    }
    
    @Override
    public AdministradorSIDAO getAdministradorSIDAO() {
        return new AdministradorSIMySQL();
    }

    @Override
    public AuxiliarDAO getAuxiliarDAO() {
        return new AuxiliarMySQL();
    }

    @Override
    public BibliotecaDAO getBibliotecaDAO() {
        return new BibliotecaMySQL();
    }

    @Override
    public BibliotecarioDAO getBibliotecarioDAO() {
        return new BibliotecarioMySQL();
    }

    @Override
    public CapacitacionDAO getCapacitacionDAO() {
        return new CapacitacionMySQL();
    }

    @Override
    public DistribucionPersonalDAO getDistribucionPersonalDAO() {
        return new DistribucionPersonalMySQL();
    }

    @Override
    public GestorDAO getGestorDAO() {
        return new GestorMySQL();
    }

    @Override
    public HoraExtraDAO getHoraExtraDAO() {
        return new HoraExtraMySQL();
    }

    @Override
    public InasistenciaDAO getInasistenciaDAO() {
        return new InasistenciaMySQL();
    }
    
    @Override
    public TipoInasistenciaDAO getTipoInasistenciaDAO() {
        return new TipoInasistenciaMySQL();
    }
    
    @Override
    public PerfilExperienciaDAO getPerfilExperenciaDAO() {
        return new PerfilExperienciaMySQL();
    }

    @Override
    public PracticanteDAO getPracticanteDAO() {
        return new PracticanteMySQL();
    }

    @Override
    public PuntosAtencionDAO getPuntosAtencionDAO() {
        return new PuntosAtencionMySQL();
    }


    @Override
    public TurnoDAO getTurnoDAO() {
        return new TurnoMySQL();
    }

    @Override
    public VacacionesDAO getVacacionesDAO() {
        return new VacacionesMySQL();
    }
    
    @Override
    public AvisoDAO getAvisoDAO() {
        return new AvisoMySQL();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioMySQL();
    }

    @Override
    public PersonalBibliotecaDAO getPersonalBibliotecaDAO() {
        return new PersonalBibliotecaMySQL();
       
    }

    @Override
    public PersonalDAO getPersonalDAO() {
        return new PersonalMySQL();
    }

}
