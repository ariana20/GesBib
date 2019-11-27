package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.DistribucionPersonalDAO;
import pe.edu.pucp.gesbibsoft.model.DistribucionPersonal;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.Personal;
import pe.edu.pucp.gesbibsoft.model.PersonalBiblioteca;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;

public class DistribucionPersonalMySQL implements DistribucionPersonalDAO {

    Connection con = null;
    CallableStatement cs = null;

    @Override
    public void insertar(DistribucionPersonal distribuPers) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_DISTRIBUCION(?,?,?,?,?)}");
            cs.setInt("_ID_PERSONAL", distribuPers.getPersonal().getId());
            cs.setInt("_ID_PUNTO_ATENCION", distribuPers.getPuntoAtencion().getId());
            cs.setDate("_FECHA",new java.sql.Date(distribuPers.getFecha().getTime()));
            cs.setTime("_HORA_INICIO", new java.sql.Time(distribuPers.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(distribuPers.getHoraFin().getTime()));
            

            cs.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void actualizar(DistribucionPersonal distribuPers) {      
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_DISTRIBUCION(?,?,?,?,?)}");
            cs.setInt("_ID_PERSONAL", distribuPers.getPersonal().getId());
            cs.setInt("_ID_PUNTO_ATENCION", distribuPers.getPuntoAtencion().getId());
            cs.setDate("_FECHA",new java.sql.Date(distribuPers.getFecha().getTime()) );
            cs.setTime("_HORA_INICIO", new java.sql.Time(distribuPers.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(distribuPers.getHoraFin().getTime()));
            
            cs.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }    }

    @Override
    public void eliminar(int idDistribuPers, int idPuntoAtencion) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_DISTRIBUCION(?, ?)}");
            cs.setInt("_ID_PERSONAL", idDistribuPers);
            cs.setInt("_ID_PUNTO_ATENCION", idPuntoAtencion);
            
            cs.executeUpdate();
            
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public ArrayList<DistribucionPersonal> listarPorPersonal(int idPersonal, Date fechaIni, Date fechaFin) {
        ArrayList<DistribucionPersonal> distribuciones = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_DISTRIBUCIONES_POR_ID_PERSONAL(?,?,?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            cStmt.setDate("_FECHA_INIT", new java.sql.Date(fechaIni.getTime()));
            cStmt.setDate("_FECHA_FIN", new java.sql.Date(fechaFin.getTime()));
            
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                DistribucionPersonal  e = new DistribucionPersonal();
                //Debo crearle memoria al punto de Atencion?
                e.getPuntoAtencion().setId(rs.getInt("ID_PUNTO_ATENCION")); 
                e.getPuntoAtencion().setNombre(rs.getString("NOMBRE_PUNTO_ATENCION"));
                e.setFecha(rs.getDate("FECHA"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                
                distribuciones.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return distribuciones;
    
    }
    
    @Override
    public ArrayList<DistribucionPersonal> listarPorFechaHora(int idPuntoAtencion, Date fechaIni, Date fechaFin) {
        ArrayList<DistribucionPersonal> listaPas = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_DISTRIBUCION_POR_FECHA_PUNTO_ATENCION(?,?,?)}");
            cStmt.setInt("_ID_PUNTO_ATENCION", idPuntoAtencion);
            cStmt.setDate("_FECHA_INI", new java.sql.Date( fechaIni.getTime()));
            cStmt.setDate("_FECHA_FIN", new java.sql.Date( fechaFin.getTime()));
            
            
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                PersonalBiblioteca personalBib=new PersonalBiblioteca();
                DistribucionPersonal dp = new DistribucionPersonal();
                
                dp.setPersonal(personalBib);
                dp.getPersonal().setId(rs.getInt("ID_USUARIO"));
                dp.getPersonal().setNombre(rs.getString("NOMBRE"));
                dp.getPersonal().setApellido(rs.getString("APELLIDO"));
                dp.getPersonal().setCodigo(rs.getString("CODIGO"));
                dp.setFecha(rs.getDate("FECHA"));
                dp.setHoraInicio(rs.getTime("HORA_INICIO"));
                dp.setHoraFin(rs.getTime("HORA_FIN"));
              
                PerfilExperiencia p=new PerfilExperiencia();
                p.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));
                personalBib.addPerfil(p);
                listaPas.add(dp);
                
                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return listaPas;
    
    }

}
