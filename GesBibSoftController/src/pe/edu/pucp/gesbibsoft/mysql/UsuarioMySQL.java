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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.UsuarioDAO;
import pe.edu.pucp.gesbibsoft.model.Usuario;
import pe.edu.pucp.gesbibsoft.model.Personal;
import pe.edu.pucp.gesbibsoft.pair.PairStringUsuario;

/**
 *
 * @author alulab14
 */
public class UsuarioMySQL implements UsuarioDAO{
    Connection con = null;
    CallableStatement cs = null;
    ResultSet rs=null;

    
    @Override
    public PairStringUsuario validarUsuario(String email, String password) {
        Usuario user=null;
        String nombreTipo = "";
       
        
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call VALIDAR_USUARIO(?,?,?,?)}");
            cs.setString("_EMAIL", email);
            cs.setString("_PASSWORD", password);

            
            cs.registerOutParameter("_ID_TIPO_USUARIO", java.sql.Types.INTEGER);
            cs.registerOutParameter("_NOMBRE_TIPO", java.sql.Types.VARCHAR);
            
            rs =cs.executeQuery();
            int tipo=cs.getInt("_ID_TIPO_USUARIO");
            if(tipo==-1){
                return new PairStringUsuario(nombreTipo, user);
            }
            nombreTipo=cs.getString("_NOMBRE_TIPO");
            rs.next();
            user=new Usuario();
            user.setId(rs.getInt("ID_USUARIO"));
            user.setApellido(rs.getString("APELLIDO"));
            user.setNombre(rs.getString("NOMBRE"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return new PairStringUsuario(nombreTipo, user);
    
    }
    
    @Override
    public ArrayList<Personal> listarUsuariosLibres(Date fecha, Time hora_inicio, Time hora_fin, String nombre_perfil) {
        ArrayList<Personal> listaPersonal = new ArrayList<>();
        Personal personal;
        
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_USUARIOS_LIBRES(?,?,?,?)}");
            cs.setDate("_FEHCA", (java.sql.Date) fecha);
            cs.setTime("_HORA_INICIO", hora_inicio);
            cs.setTime("_HORA_FIN", hora_fin);
            cs.setString("_NOMBRE_PERFIL", nombre_perfil);
            
            rs =cs.executeQuery();
            
            while (rs.next()) {
                personal = new Personal();
                personal.setId(rs.getInt("ID_USUARIO"));
                personal.setCodigo(rs.getString("CODIGO"));
                personal.setNombre(rs.getString("NOMBRE"));
                personal.setApellido(rs.getString("APELLIDO"));
                personal.setContrasenia(Integer.toString(rs.getInt("CUMPLE_PERFIL")));
                listaPersonal.add(personal);
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
        return listaPersonal;
    }
    
}
