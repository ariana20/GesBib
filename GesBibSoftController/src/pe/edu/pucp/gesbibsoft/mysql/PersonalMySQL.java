/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.PersonalDAO;

/**
 *
 * @author Ariana
 */
public class PersonalMySQL implements PersonalDAO{
    
    
    Connection con = null;
    CallableStatement cs = null;
    
    @Override
    public String hallarTipoUsuario(int idUsuario) {
        String tipo="";
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call GET_TIPO_PERSONAL(?,?)}");
            cs.setInt("_ID_USUARIO", idUsuario);
            
            cs.registerOutParameter("_TIPO_DEL_TIPEJO", java.sql.Types.INTEGER);
            cs.executeUpdate();
            tipo=cs.getString("_TIPO_DEL_TIPEJO");
            
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return tipo;
    
    }
    
}
