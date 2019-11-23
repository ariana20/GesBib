package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.Dia_CapacitacionDAO;
import pe.edu.pucp.gesbibsoft.model.Dia_Capacitacion;

public class Dia_CapacitacionMySQL implements Dia_CapacitacionDAO{
    
    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public void insertar(Dia_Capacitacion dia_capa) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            
            cs = con.prepareCall("{call INSERTAR_DIA_CAPACITACION(?,?,?,?,?,?)}");
            
            cs.setInt("_ID_CAPACITACION", dia_capa.getCapacitacion().getId());
            cs.setTime("_HORA_INI", new java.sql.Time(dia_capa.getHora_ini().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(dia_capa.getHora_fin().getTime()));
            cs.setDate("_FECHA", new java.sql.Date(dia_capa.getFecha().getTime()));
            cs.setInt("_ACTIVO", 1);
            
            cs.registerOutParameter("_ID_DIA_CAPACITACION", java.sql.Types.INTEGER);
            
            cs.executeUpdate();
            dia_capa.setIdDiaCapacitacion(cs.getInt("_ID_CAPACITACION"));

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
    public int actualizar(Dia_Capacitacion dia_capa) {
        int result=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_DIA_CAPACITACION(?,?,?,?,?,?)}");
            
            
            cs.setInt("_ID_DIA_CAPACITACION", dia_capa.getIdDiaCapacitacion());
            cs.setInt("_ID_CAPACITACION", dia_capa.getCapacitacion().getId());
            java.sql.Time time1 = new java.sql.Time(dia_capa.getHora_ini().getTime());
            java.sql.Time time2 = new java.sql.Time(dia_capa.getHora_fin().getTime());
            java.sql.Date date1 = new java.sql.Date(dia_capa.getFecha().getTime());
            
            cs.setTime("_HORA_INI", time1);
            cs.setTime("_HORA_FIN", time2);
            cs.setDate("_FECHA", date1);
            cs.setInt("_ACTIVO", dia_capa.getActivo());
            
            cs.executeUpdate();
            result=0;
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
    public int eliminar(int idDia_capa) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_DIA_CAPACITACION(?)}");
            cs.setInt("_ID_DIA_CAPACITACION", idDia_capa);
            
            cs.executeUpdate();
            resultado=1;
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public ArrayList<Dia_Capacitacion> listar(int id_capacitacion) {
        ArrayList<Dia_Capacitacion> dias_capacitacion = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            
            cs = con.prepareCall("{call LISTAR_DIAS_CAPACITACION(?)}");
            cs.setInt("_ID_CAPACITACION", id_capacitacion);
            
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                Dia_Capacitacion e = new Dia_Capacitacion();
                
                e.setIdDiaCapacitacion(rs.getInt("ID_ID_CAPACITACION"));
                e.setHora_ini(rs.getTime("HORA_INICIO"));
                e.setHora_fin(rs.getTime("HORA_FIN"));
                e.setFecha(rs.getDate("FECHA"));
                e.setActivo(1);
                
                dias_capacitacion.add(e);
            }
            
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return dias_capacitacion;
    }
}
