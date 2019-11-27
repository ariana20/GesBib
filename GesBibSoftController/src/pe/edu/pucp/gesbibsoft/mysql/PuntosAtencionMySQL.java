/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.PuntosAtencionDAO;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;

/**
 *
 * @author alulab14
 */
public class PuntosAtencionMySQL implements PuntosAtencionDAO {

    Connection con = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public int insertar(PuntosAtencion puntoAt) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PUNTO_ATENCION(?,?,?,?,?,?,?)}");
            cs.setInt("_PISO", puntoAt.getPiso());
            cs.setString("_NOMBRE", puntoAt.getNombre());
            cs.setInt("_CANTIDAD_MIN_PERSONAL", puntoAt.getCant_min_pers());
            cs.setInt("_CANTIDAD_OPT_PERSONAL", puntoAt.getCant_opt_pers());
            cs.setInt("ID_BIBLIOTECA", puntoAt.getBiblioteca().getId());
            cs.setInt("ID_PERFIL_EXPERIENCIA", puntoAt.getPerfilExperiencia().getId());
            cs.registerOutParameter("_ID_PUNTO_ATENCION", java.sql.Types.INTEGER);
            cs.executeUpdate();
            puntoAt.setId(cs.getInt("_ID_PUNTO_ATENCION"));
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
    public int actualizar(PuntosAtencion puntoAt) {
        int resul=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PUNTO_ATENCION(?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_PUNTO_ATENCION", puntoAt.getId());
            cs.setInt("_PISO", puntoAt.getPiso());
            cs.setString("_NOMBRE", puntoAt.getNombre());
            cs.setInt("_CANTIDAD_MIN_PERSONAL", puntoAt.getCant_min_pers());
            cs.setInt("_CANTIDAD_OPT_PERSONAL", puntoAt.getCant_opt_pers());
            cs.setInt("_ACTIVO", 1);
            cs.setInt("ID_BIBLIOTECA", puntoAt.getBiblioteca().getId());
            cs.setInt("ID_PERFIL_EXPERIENCIA", puntoAt.getPerfilExperiencia().getId());
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
    public void eliminar(int idPuntosAt) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PUNTO_ATENCION(?)}");
            cs.setInt("_ID", idPuntosAt);
            
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
    public ArrayList<PuntosAtencion> listar(int idBiblioteca) {
        ArrayList<PuntosAtencion> listaPuntosAtencion = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_PUNTOS_ATENCION_X_BIBLIO(?)}");
            cStmt.setInt("_ID_BIBLIOTECA", idBiblioteca);
            
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                PuntosAtencion e = new PuntosAtencion();
                PerfilExperiencia pe=new PerfilExperiencia();
                e.setPerfilExperiencia(pe);
                e.setId(rs.getInt("ID_PUNTO_ATENCION"));
                e.setPiso(rs.getInt("PISO"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setCant_min_pers(rs.getInt("CANTIDAD_MIN_PERSONAL"));
                e.setCant_opt_pers(rs.getInt("CANTIDAD_OPT_PERSONAL"));
                e.getPerfilExperiencia().setId(rs.getInt("ID_PERFIL_EXPERIENCIA"));
                e.getPerfilExperiencia().setNombrePerfil(rs.getString("NOMBRE_PERFIL"));
                listaPuntosAtencion.add(e);
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
        return listaPuntosAtencion;
    }

}
