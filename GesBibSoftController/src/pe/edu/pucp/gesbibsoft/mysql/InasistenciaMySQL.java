package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.InasistenciaDAO;
import pe.edu.pucp.gesbibsoft.model.Inasistencia;
import pe.edu.pucp.gesbibsoft.model.TipoInasistencia;

public class InasistenciaMySQL implements InasistenciaDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;

    @Override
    public int insertar(Inasistencia inasistencia) {
        int result=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_INASISTENCIA(?,?,?,?,?,?,?)}");
            
            cs.setInt("_ID_PERSONAL", inasistencia.getPersonal().getId());
            cs.setInt("_ID_TIPO_INASISTENCIA", inasistencia.getTipoInasistencia().getId());
            cs.setDate("_FECHA", new java.sql.Date(inasistencia.getFecha().getTime()));
            cs.setString("_MOTIVO", inasistencia.getMotivo());
            cs.setTime("_HORA_INICIO", new java.sql.Time(inasistencia.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(inasistencia.getHoraFin().getTime()));
            
            cs.registerOutParameter("_ID_INASISTENCIA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            inasistencia.setId(cs.getInt("_ID_INASISTENCIA"));
            result=1;
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public int actualizar(Inasistencia inasistencia) {
        int res=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_INASISTENCIA(?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_INASISTENCIA", inasistencia.getId());
            cs.setInt("_ID_PERSONAL", inasistencia.getPersonal().getId());
            cs.setInt("_ID_TIPO_INASISTENCIA", inasistencia.getTipoInasistencia().getId());
            cs.setDate("_FECHA", new java.sql.Date(inasistencia.getFecha().getTime()));
            cs.setString("_MOTIVO", inasistencia.getMotivo());
            cs.setTime("_HORA_INICIO", new java.sql.Time(inasistencia.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(inasistencia.getHoraFin().getTime()));
            cs.setInt("_ACTIVO", 1);
            cs.setInt("_JUSTIFICADO", inasistencia.getJustificado());
//            if(inasistencia.isJustificado())
//                cs.setInt("_JUSTIFICADO",1);
//            else
//                cs.setInt("_JUSTIFICADO",0);
            cs.executeUpdate();
            res=1;
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return res;

    }

    @Override
    public void eliminar(int idInasistencia) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_INASISTENCIA(?)}");
            cs.setInt("_ID_INASISTENCIA", idInasistencia);
            
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
    public ArrayList<Inasistencia> listarPorPersonal(int idPersonal) {
        ArrayList<Inasistencia> inasistencias = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_INASISTENCIAS(?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                Inasistencia  e = new Inasistencia();
                e.setId(rs.getInt("ID_INASISTENCIA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setMotivo(rs.getString("MOTIVO"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                
                inasistencias.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return inasistencias;
    }
    
    @Override
    public ArrayList<Inasistencia> listarInasistencias(int idPersonal) {
        ArrayList<Inasistencia> inasistencias = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_INASISTENCIAS(?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
                    
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Inasistencia e = new Inasistencia();
                e.setId(rs.getInt("ID_INASISTENCIA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setMotivo(rs.getString("MOTIVO"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                
                inasistencias.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return inasistencias;  
    }
    
    @Override
    public ArrayList<Inasistencia> listarInasistenciasPorFecha(int idPersonal, Date fecha) {
        ArrayList<Inasistencia> inasistencias = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_INASISTENCIAS_POR_FECHA(?,?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            cStmt.setDate("_FECHA", new java.sql.Date(fecha.getTime()));      
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Inasistencia e = new Inasistencia();
                e.setId(rs.getInt("ID_INASISTENCIA"));
                e.setFecha(rs.getDate("FECHA"));
                TipoInasistencia a=new TipoInasistencia();
                e.setTipoInasistencia(a);
                e.getTipoInasistencia().setId(rs.getInt("ID_TIPO_INASISTENCIA"));
                e.getTipoInasistencia().setNombre(rs.getString("NOMBRE"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setMotivo(rs.getString("MOTIVO"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                
                inasistencias.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return inasistencias;  
    }
    
    @Override
    public ArrayList<Inasistencia> listarHorasLibresDePersonal(int idPersonal, int estado) {
        ArrayList<Inasistencia> horasLibre = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_HORAS_LIBRE_DE_PERSONAL(?,?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            cStmt.setInt("_JUSTIFICADO", estado);
                    
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Inasistencia e = new Inasistencia();
                e.setId(rs.getInt("ID_INASISTENCIA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setMotivo(rs.getString("MOTIVO"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                
                horasLibre.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return horasLibre;  
        
        
    }
    
    @Override
    public ArrayList<Inasistencia> listarHorasLibresDePersonalPorFecha(int idPersonal, int estado, Date fecha) {
        ArrayList<Inasistencia> horasLibre = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_HORAS_LIBRE_DE_PERSONAL_POR_FECHA(?,?,?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            cStmt.setInt("_JUSTIFICADO", estado);
            cStmt.setDate("_FECHA", new java.sql.Date(fecha.getTime()));
                    
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Inasistencia e = new Inasistencia();
                e.setId(rs.getInt("ID_INASISTENCIA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setMotivo(rs.getString("MOTIVO"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                
                horasLibre.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return horasLibre;  
        
        
    }

    @Override
    public ArrayList<Inasistencia> listarTodasHorasLibresDePersonal(int idPersonal) {
        ArrayList<Inasistencia> inasistencias = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_TODAS_HORAS_LIBRE(?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                Inasistencia  e = new Inasistencia();
                e.setId(rs.getInt("ID_INASISTENCIA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setMotivo(rs.getString("MOTIVO"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                
                inasistencias.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return inasistencias;
    }

}
