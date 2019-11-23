/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import pe.edu.pucp.gesbibsoft.dao.VacacionesDAO;
import pe.edu.pucp.gesbibsoft.model.Vacaciones;

/**
 *
 * @author alulab14
 */
public class VacacionesMySQL implements VacacionesDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public void insertar(Vacaciones vacaciones) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_VACACIONES(?,?,?,?)}");
            cs.setInt("_ID_PERSONAL", vacaciones.getPersonal().getId());
            cs.setDate("_FECHA_INICIO", new java.sql.Date(vacaciones.getFecha_inicio().getTime()));
            cs.setDate("_FECHA_FIN", new java.sql.Date(vacaciones.getFecha_fin().getTime()));
            cs.registerOutParameter("_ID_VACACIONES", java.sql.Types.INTEGER);
            cs.executeUpdate();
            vacaciones.setId(cs.getInt("_ID_VACACIONES"));

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
    public void actualizar(Vacaciones vacaciones) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_VACACIONES(?,?,?,?)}");
            cs.setInt("_ID_VACACIONES", vacaciones.getId());
            cs.setInt("_ID_PERSONAL", vacaciones.getPersonal().getId());
            java.sql.Date date1 = new java.sql.Date(vacaciones.getFecha_inicio().getTime());
            cs.setDate("_FECHA_INICIO", date1);
            java.sql.Date date2 = new java.sql.Date(vacaciones.getFecha_fin().getTime());
            cs.setDate("_FECHA_FIN", date2);
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
    public void eliminar(int idVacaciones) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_VACACIONES(?)}");
            cs.setInt("_ID", idVacaciones);
            
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
    public ArrayList<Vacaciones> listar() {
        ArrayList<Vacaciones> listaVacaciones = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_VACACIONES()}");
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Vacaciones e = new Vacaciones();
                e.setId(rs.getInt("ID_VACACIONES"));
                e.setFecha_inicio(rs.getDate("FECHA_INICIO"));
                e.setFecha_fin(rs.getDate("FECHA_FIN"));
                listaVacaciones.add(e);
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
        return listaVacaciones;
    }

}
