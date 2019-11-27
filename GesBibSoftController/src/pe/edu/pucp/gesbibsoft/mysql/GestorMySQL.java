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
import pe.edu.pucp.gesbibsoft.dao.GestorDAO;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Gestor;

public class GestorMySQL implements GestorDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public int insertar(Gestor gestor) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_GESTOR(?,?,?,?,?,?,?,?,?)}");
            cs.setString("_NOMBRE", gestor.getNombre());
            cs.setString("_APELLIDO", gestor.getApellido());
            cs.setString("_EMAIL", gestor.getEmail());
            cs.setString("_PASSWORD", gestor.getContrasenia());
            cs.setString("_CODIGO", gestor.getCodigo());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(gestor.getFecha_ingreso().getTime()));
            cs.setFloat("_TOTAL_HORA_EXTRA", gestor.getTotalHorasExtra());
            cs.setInt("_ID_BIBLIOTECA", gestor.getBiblioteca().getId());
            cs.registerOutParameter("_ID_USUARIO", java.sql.Types.INTEGER);
            
            cs.executeUpdate();
            gestor.setId(cs.getInt("_ID_USUARIO"));
            
            
            
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
    public int actualizar(Gestor gestor) {
        int resul=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_GESTOR(?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_GESTOR", gestor.getId());
            
            cs.setString("_NOMBRE", gestor.getNombre());
            cs.setString("_APELLIDO", gestor.getApellido());
            cs.setString("_EMAIL", gestor.getEmail());
            cs.setString("_PASSWORD", gestor.getContrasenia());
            cs.setInt("_ACTIVO", 1);
            cs.setString("_CODIGO", gestor.getCodigo());
            //cs.setBytes("_FOTO", gestor.getFoto());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(gestor.getFecha_ingreso().getTime()));
            //cs.setInt("_ID_TURNO", gestor.getTurno().getId());
            cs.setInt("_ID_BIBLIOTECA", gestor.getBiblioteca().getId());
            
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
    public int eliminar(int idGestor) {
        int resul=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_GESTOR(?)}");
            cs.setInt("_ID_GESTOR", idGestor);
            
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
    public ArrayList<Gestor> listar(String nombre, String apellido) {
        ArrayList<Gestor> gestores = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_GESTORES_ACTIVOS(?,?)}");
            cStmt.setString("_NOMBRE", nombre);
            cStmt.setString("_APELLIDO", apellido);
                    
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Gestor e = new Gestor();
                e.setId(rs.getInt("ID_GESTOR"));
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
                gestores.add(e);
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
        return gestores;
    }

    @Override
    public Biblioteca getBiblioteca(int idGestor) {
        Biblioteca bib=new Biblioteca();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call GET_BIBLIOTECA(?,?,?)}");
            cs.setInt("_ID_GESTOR", idGestor);
            
            cs.registerOutParameter("_ID_BIBLIOTECA", java.sql.Types.INTEGER);
            cs.registerOutParameter("_NOMBRE_BIBLIOTECA", java.sql.Types.VARCHAR);
            
            cs.executeUpdate();
            bib.setId(cs.getInt("_ID_BIBLIOTECA"));
            bib.setNombre(cs.getString("_NOMBRE_BIBLIOTECA"));
            
            
  
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return bib;
    }

}
