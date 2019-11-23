package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.TurnoDAO;
import pe.edu.pucp.gesbibsoft.model.Turno;

public class TurnoMySQL implements TurnoDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;

    @Override
    public void insertar(Turno turno) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_TURNO(?,?,?,?,?,?)}");
            cs.setString("_SEMESTRE", String.valueOf(turno.getSemestre()));
            cs.setTime("_HORA_INICIO", new java.sql.Time(turno.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(turno.getHoraFin().getTime()));
            cs.setTime("_REFRIGERIO_INICIO", new java.sql.Time(turno.getRefrigerioInicio().getTime()));
            cs.setTime("_REFRIGERIO_FIN", new java.sql.Time(turno.getRefrigerioFin().getTime()));
            
            cs.registerOutParameter("_ID_TURNO", java.sql.Types.INTEGER);
            cs.executeUpdate();
            turno.setId(cs.getInt("_ID_TURNO"));

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
    public void actualizar(Turno turno) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_TURNO(?,?,?,?,?,?)}");
            cs.setInt("_ID_TURNO", turno.getId());
            cs.setString("_SEMESTRE", turno.getSemestre());
            cs.setTime("_HORA_INICIO", new java.sql.Time(turno.getHoraInicio().getTime()));
            cs.setTime("_HORA_FIN", new java.sql.Time(turno.getHoraFin().getTime()));
            cs.setTime("_REFRIGERIO_INICIO", new java.sql.Time(turno.getRefrigerioInicio().getTime()));
            cs.setTime("_REFRIGERIO_FIN", new java.sql.Time(turno.getRefrigerioFin().getTime()));
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
    public void eliminar(int idTurno) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_TURNO(?)}");
            cs.setInt("_ID", idTurno);
            
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
    public ArrayList<Turno> listar() {
        ArrayList<Turno> turnos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_TURNOS()}");
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                Turno  e = new Turno();
                e.setId(rs.getInt("ID_TURNO"));
                e.setSemestre(rs.getString("SEMESTRE"));
                e.setHoraInicio(rs.getTime("HORA_INICIO"));
                e.setHoraFin(rs.getTime("HORA_FIN"));
                e.setRefrigerioInicio(rs.getTime("REFRIGERIO_INICIO"));
                e.setRefrigerioFin(rs.getTime("REFRIGERIO_FIN"));
                
                turnos.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return turnos;
    }
}


