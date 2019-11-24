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
import pe.edu.pucp.gesbibsoft.dao.BibliotecarioDAO;
import pe.edu.pucp.gesbibsoft.enums.DiaSemana;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Bibliotecario;

public class BibliotecarioMySQL implements BibliotecarioDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public int insertar(Bibliotecario bibliotecario, TipoPersonal idTipoPersonal) {
        int resultado = 0;
        
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PERSONAL_BIBLIOTECA(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("_NOMBRE", bibliotecario.getNombre());
            cs.setString("_APELLIDO", bibliotecario.getApellido());
            cs.setString("_EMAIL", bibliotecario.getEmail());
            cs.setString("_PASSWORD", bibliotecario.getContrasenia());
            cs.setString("_CODIGO", bibliotecario.getCodigo());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(bibliotecario.getFecha_ingreso().getTime()));
            cs.setFloat("_TOTAL_HORA_EXTRA", bibliotecario.getTotalHorasExtra());
            cs.setInt("_ID_TIPO_PERSONAL",idTipoPersonal.value);
            cs.setInt("_ID_BIBLIOTECA", bibliotecario.getBiblioteca().getId());
            cs.setString("_DIA_SEMANA", bibliotecario.getDiaSemana());
            
            cs.registerOutParameter("_ID_PERSONAL_BIBLIOTECA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            bibliotecario.setId(cs.getInt("_ID_PERSONAL_BIBLIOTECA"));
            resultado=1;
        } catch (SQLException ex) {
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
    public int actualizar(Bibliotecario bibliotecario, TipoPersonal idTipoPersonal) {
        int res=0;
        try{
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PERSONAL_BIBLIOTECA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_PERSONAL_BIBLIOTECA", bibliotecario.getId());
            cs.setInt("_ID_BIBLIOTECA", bibliotecario.getBiblioteca().getId());
            cs.setInt("_ID_TIPO_PERSONAL",idTipoPersonal.value);
            cs.setInt("_ACTIVO",1);
            cs.setString("_DIA_SEMANA", bibliotecario.getDiaSemana());
            cs.setString("_NOMBRE", bibliotecario.getNombre());
            cs.setString("_APELLIDO", bibliotecario.getApellido());
            cs.setString("_EMAIL", bibliotecario.getEmail());
            cs.setString("_PASSWORD", bibliotecario.getContrasenia());
            cs.setString("_CODIGO", bibliotecario.getCodigo());
            cs.setDate("_FECHA_INGRESO",  new java.sql.Date(bibliotecario.getFecha_ingreso().getTime()));
            cs.setBytes("_FOTO", bibliotecario.getFoto());
            cs.setInt("_ID_TURNO", bibliotecario.getTurno().getId());
            cs.setInt("_ID_TIPO_USUARIO", 2);
            cs.executeUpdate();
            res=1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return res;
    }

    @Override
    public void eliminar(int idbibliotecario) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_BIBLIOTECARIO(?)}");
            cs.setInt("_ID", idbibliotecario);
            
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
    public ArrayList<Bibliotecario> listar(String nombre, String apellido) {
        ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_PERSONAL_BIBLIOTECA_ACTIVO(?,?,?)}");
            cStmt.setString("_NOMBRE", nombre);
            cStmt.setString("_APELLIDO", apellido);
            cStmt.setInt("_ID_TIPO_PERSONAL", TipoPersonal.Bibliotecario.value);
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Bibliotecario e = new Bibliotecario();
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
                //e.setDiaSemana(DiaSemana.valueOf(rs.getString("DIA_SEMANA")));
                
                //e.setFoto(rs.getBytes("FOTO"));
                bibliotecarios.add(e);
            }
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return bibliotecarios;
    }

}
