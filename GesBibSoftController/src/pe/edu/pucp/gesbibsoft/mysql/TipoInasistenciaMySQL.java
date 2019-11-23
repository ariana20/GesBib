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
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.TipoInasistenciaDAO;
import pe.edu.pucp.gesbibsoft.model.TipoInasistencia;

public class TipoInasistenciaMySQL implements TipoInasistenciaDAO{
    Connection con = null;
    CallableStatement cs = null;
    
    @Override
    public void insertar(TipoInasistencia tipoInasistencia) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_TIPO_INASISTENCIA(?,?)}");
            cs.setString("_NOMBRE", tipoInasistencia.getNombre());
            
            cs.registerOutParameter("_ID_PERFIL_EXPERIENCIA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            tipoInasistencia.setId(cs.getInt("_ID_TIPO_INASISTENCIA"));

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
    public void actualizar(TipoInasistencia tipoInasistencia) {
        try {
            cs = con.prepareCall("{call ACTUALIZAR_TIPO_INASISTENCIA(?,?)}");
            cs.setInt("_ID_TIPO_INASISTENCIA", tipoInasistencia.getId());
            cs.setString("_NOMBRE", tipoInasistencia.getNombre());
            
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
    public void eliminar(int idTipoInasistencia) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_TIPO_INASISTENCIA(?)}");
            cs.setInt("_ID_TIPO_INASISTENCIA", idTipoInasistencia);
            
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
    public ArrayList<TipoInasistencia> listar() {
        ArrayList<TipoInasistencia> tiposInasistencia = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_TIPO_INASISTENCIA()}");
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                TipoInasistencia e = new TipoInasistencia();
                e.setId(rs.getInt("ID_TIPO_INASISTENCIA"));
                e.setNombre(rs.getString("NOMBRE"));

                tiposInasistencia.add(e);
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
        return tiposInasistencia;
    
    
    }
    
    
}
