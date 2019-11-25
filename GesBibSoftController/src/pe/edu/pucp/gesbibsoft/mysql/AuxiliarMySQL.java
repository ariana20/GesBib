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
import pe.edu.pucp.gesbibsoft.dao.AuxiliarDAO;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Auxiliar;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;


public class AuxiliarMySQL implements AuxiliarDAO {
    
    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;
    @Override
    public int insertar(Auxiliar auxiliar, TipoPersonal idTipoPersonal) {
        int resultado=0;
        try{
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PERSONAL_BIBLIOTECA(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("_NOMBRE", auxiliar.getNombre());
            cs.setString("_APELLIDO", auxiliar.getApellido());
            cs.setString("_EMAIL", auxiliar.getEmail());
            cs.setString("_PASSWORD", auxiliar.getContrasenia());
            cs.setString("_CODIGO", auxiliar.getCodigo());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(auxiliar.getFecha_ingreso().getTime()));
            cs.setFloat("_TOTAL_HORA_EXTRA", auxiliar.getTotalHorasExtra());
            cs.setInt("_ID_TIPO_PERSONAL",idTipoPersonal.value);
            cs.setInt("_ID_BIBLIOTECA", auxiliar.getBiblioteca().getId());
            cs.setString("_DIA_SEMANA", "0");
            
            cs.registerOutParameter("_ID_PERSONAL_BIBLIOTECA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            auxiliar.setId(cs.getInt("_ID_PERSONAL_BIBLIOTECA"));
    
            resultado=1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(Auxiliar auxiliar, TipoPersonal idTipoPersonal) {
        int resul=0;
        try{
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PERSONAL_BIBLIOTECA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_PERSONAL_BIBLIOTECA", auxiliar.getId());
            cs.setInt("_ID_BIBLIOTECA", auxiliar.getBiblioteca().getId());
            cs.setInt("_ID_TIPO_PERSONAL",idTipoPersonal.value);
            cs.setInt("_ACTIVO",1);
            cs.setString("_DIA_SEMANA", "");
            cs.setString("_NOMBRE", auxiliar.getNombre());
            cs.setString("_APELLIDO", auxiliar.getApellido());
            cs.setString("_EMAIL", auxiliar.getEmail());
            cs.setString("_PASSWORD", auxiliar.getContrasenia());
            cs.setString("_CODIGO", auxiliar.getCodigo());
            cs.setDate("_FECHA_INGRESO",  new java.sql.Date(auxiliar.getFecha_ingreso().getTime()));
            cs.setBytes("_FOTO", auxiliar.getFoto());
            cs.setInt("_ID_TURNO", auxiliar.getTurno().getId());
            cs.setInt("_ID_TIPO_USUARIO", 2);
            cs.executeUpdate();
            resul=1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resul;
               
    }

    @Override
    public void eliminar(int idAuxiliar) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_AUXILIAR_BIBLIOTECA(?)}");
            cs.setInt("_ID", idAuxiliar);
            
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
    public ArrayList<Auxiliar> listar(String nombre, String apellido) {
        ArrayList<Auxiliar> auxiliares = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_PERSONAL_BIBLIOTECA_ACTIVO(?,?,?)}");
            cStmt.setString("_NOMBRE", nombre);
            cStmt.setString("_APELLIDO", apellido);
            cStmt.setInt("_ID_TIPO_PERSONAL", TipoPersonal.Auxiliar.value);
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Auxiliar e = new Auxiliar();
                e.setId(rs.getInt("ID_PERSONAL_BIBLIOTECA"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setApellido(rs.getString("APELLIDO"));
                e.setEmail(rs.getString("EMAIL"));
                e.setContrasenia(rs.getString("PASSWORD"));
                e.setCodigo(rs.getString("CODIGO"));
                e.setFecha_ingreso(rs.getDate("FECHA_INGRESO"));
                e.setTotalHorasExtra(rs.getFloat("TOTAL_HORA_EXTRA"));
                e.getTurno().setId(rs.getInt("ID_TURNO"));
                Biblioteca b=new Biblioteca();
                b.setId(rs.getInt("ID_BIBLIOTECA"));
                e.setBiblioteca(b);
                
                //e.setFoto(rs.getBytes("FOTO"));
                auxiliares.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return auxiliares;
    }
    
}
