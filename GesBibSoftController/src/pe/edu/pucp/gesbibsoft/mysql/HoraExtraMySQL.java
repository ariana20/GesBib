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
import pe.edu.pucp.gesbibsoft.dao.HoraExtraDAO;
import pe.edu.pucp.gesbibsoft.model.HoraExtra;

public class HoraExtraMySQL implements HoraExtraDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;

    @Override
    public int insertar(HoraExtra horaExtra) {
        int result=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_HORA_EXTRA(?,?,?,?,?,?)}");
            cs.setInt("_ID_PERSONAL", horaExtra.getPersonal().getId());
            cs.setDate("_FECHA", new java.sql.Date(horaExtra.getFecha().getTime()));
            cs.setString("_DESCRIPCION", horaExtra.getDescripcion());
            cs.setTime("_HORA_INICIO", new java.sql.Time(horaExtra.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(horaExtra.getHoraFin().getTime()));
            cs.registerOutParameter("_ID_HORA_EXTRA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            horaExtra.setId(cs.getInt("_ID_HORA_EXTRA"));
            result=1;

        } catch (SQLException ex) {
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
    public int actualizar(HoraExtra horaExtra) {
        int resul=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_HORA_EXTRA(?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_HORA_EXTRA", horaExtra.getId());
            cs.setInt("_ID_PERSONAL", horaExtra.getPersonal().getId());
            cs.setDate("_FECHA", new java.sql.Date(horaExtra.getFecha().getTime()));
            cs.setTime("_HORA_INICIO", new java.sql.Time(horaExtra.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(horaExtra.getHoraFin().getTime()));
            cs.setString("_DESCRIPCION", horaExtra.getDescripcion());
            cs.setInt("_JUSTIFICADO", horaExtra.getJustificado());
            cs.setInt("_ACTIVO", 1);

            cs.executeUpdate();
            resul=1;
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resul;
        


    }

    @Override
    public void eliminar(int idHoraExtra) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_HORA_EXTRA(?)}");
            cs.setInt("_ID", idHoraExtra);
          
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
    public ArrayList<HoraExtra> listar(int idPersonal) {
        ArrayList<HoraExtra> horasExtra = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_HORAS_EXTRA_POR_ID_PERSONAL(?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                HoraExtra e = new HoraExtra();
                e.setId(rs.getInt("ID_HORA_EXTRA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setDescripcion(rs.getString("DESCRIPCION"));
                e.setCantidadHoras(rs.getFloat("CANTIDAD_HORAS"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setJustificado(rs.getInt("JUSTIFICADO"));
                horasExtra.add(e);
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
        return horasExtra;
    }

    @Override
    public ArrayList<HoraExtra> listarPorFecha(int idPersonal, Date fecha) {
        ArrayList<HoraExtra> horasExtra = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_HORAS_EXTRA_POR_ID_PERSONAL_Y_FECHA(?,?)}");
            cStmt.setInt("_ID_PERSONAL", idPersonal);
            cStmt.setDate("_FECHA", new java.sql.Date(fecha.getTime()));
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                HoraExtra e = new HoraExtra();
                e.setId(rs.getInt("ID_HORA_EXTRA"));
                e.setFecha(rs.getDate("FECHA"));
                e.setDescripcion(rs.getString("DESCRIPCION"));
                e.setCantidadHoras(rs.getFloat("CANTIDAD_HORAS"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                horasExtra.add(e);
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
        return horasExtra;
    }
    
    
    
}


