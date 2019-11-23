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
import pe.edu.pucp.gesbibsoft.dao.PerfilExperienciaDAO;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;

/**
 *
 * @author alulab14
 */
public class PerfilExperienciaMySQL implements PerfilExperienciaDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public int insertar(PerfilExperiencia perfilExperiencia) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PERFIL_EXPERIENCIA(?,?)}");
            cs.setString("_NOMBRE_PERFIL", perfilExperiencia.getNombrePerfil());
            cs.registerOutParameter("_ID_PERFIL_EXPERIENCIA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            perfilExperiencia.setId(cs.getInt("_ID_PERFIL_EXPERIENCIA"));
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
    public void actualizar(PerfilExperiencia perfilExperiencia) {
        try {
            cs = con.prepareCall("{call ACTUALIZAR_PERFIL_EXPERIENCIA(?,?)}");
            cs.setInt("_ID_PERFIL_EXPERIENCIA", perfilExperiencia.getId());
            cs.setString("_NOMBRE_PERFIL", perfilExperiencia.getNombrePerfil());
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
    public void eliminar(int idPerfilExperiencia) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PERFIL_EXPERIENCIA(?)}");
            cs.setInt("_ID", idPerfilExperiencia);
            
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
    public ArrayList<PerfilExperiencia> listar() {
        ArrayList<PerfilExperiencia> perfilesExperiencia = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_PERFILES_EXPERIENCIA()}");
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                PerfilExperiencia e = new PerfilExperiencia();
                e.setId(rs.getInt("ID_PERFIL_EXPERIENCIA"));
                e.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));

                perfilesExperiencia.add(e);
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
        return perfilesExperiencia;
    }

}
