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
import pe.edu.pucp.gesbibsoft.dao.PersonalBibliotecaDAO;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.PersonalBiblioteca;

/**
 *
 * @author Ariana
 */
public class PersonalBibliotecaMySQL implements PersonalBibliotecaDAO{
    
    Connection con = null;
    CallableStatement cs = null;
    
    @Override
    public int asignarPerfil(PersonalBiblioteca personalBiblioteca,PerfilExperiencia perfilExperiencia) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ASIGNAR_PERFIL(?,?)}");
            cs.setInt("_ID_PERFIL_EXPERIENCIA", perfilExperiencia.getId());
            cs.setInt("_ID_PERSONAL_BIBLIOTECA", personalBiblioteca.getId());
            
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
    
}
