
package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.dao.AdministradorSIDAO;
import pe.edu.pucp.gesbibsoft.model.AdministradorSI;
import pe.edu.pucp.gesbibsoft.config.DBManager;

public class AdministradorSIMySQL implements AdministradorSIDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public void insertar(AdministradorSI adminSI) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_ADMINISTRADOR_SI(?,?,?,?,?)}");
            
            cs.setString("_APELLIDO", adminSI.getApellido());
            cs.setString("_NOMBRE", adminSI.getNombre());
            cs.setString("_EMAIL", adminSI.getEmail());
            cs.setString("_PASSWORD", adminSI.getContrasenia());
            cs.registerOutParameter("_ID_USUARIO", java.sql.Types.INTEGER);
            cs.executeUpdate();
            adminSI.setId(cs.getInt("_ID_USUARIO"));
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(AdministradorSI adminSI) {
        try{
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_ADMINISTRADOR(?,?,?,?,?)}");
            cs.setInt("_ID", adminSI.getId());
            cs.setString("_NOMBRE", adminSI.getNombre());
            cs.setString("_APELLIDO", adminSI.getApellido());
            cs.setString("_EMAIL", adminSI.getEmail());
            cs.setString("_PASSWORD", adminSI.getContrasenia());
           
            cs.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void eliminar(int idAdministradorSI) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_ADMINISTRADOR_SI(?)}");
            cs.setInt("_ID", idAdministradorSI);
            
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
    public ArrayList<AdministradorSI> listar() {
        ArrayList<AdministradorSI> administradoresSI = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_ADMINISTRADORES_SI()}");
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                AdministradorSI  e = new AdministradorSI();
                e.setId(rs.getInt("ID_USUARIO"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setApellido(rs.getString("APELLIDO"));
                e.setEmail(rs.getString("EMAIL"));
                e.setContrasenia(rs.getString("PASSWORD"));
                administradoresSI.add(e);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return administradoresSI;
    }


}
