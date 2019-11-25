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
import pe.edu.pucp.gesbibsoft.dao.PracticanteDAO;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Practicante;

public class PracticanteMySQL implements PracticanteDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public int insertar(Practicante practicante, TipoPersonal idTipoPersonal) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PERSONAL_BIBLIOTECA(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("_NOMBRE", practicante.getNombre());
            cs.setString("_APELLIDO", practicante.getApellido());
            cs.setString("_EMAIL", practicante.getEmail());
            cs.setString("_PASSWORD", practicante.getContrasenia());
            cs.setString("_CODIGO", practicante.getCodigo());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(practicante.getFecha_ingreso().getTime()));
            cs.setFloat("_TOTAL_HORA_EXTRA", practicante.getTotalHorasExtra());
            cs.setInt("_ID_TIPO_PERSONAL",idTipoPersonal.value);
            cs.setInt("_ID_BIBLIOTECA", practicante.getBiblioteca().getId());
            cs.setString("_DIA_SEMANA", "0");
            
            cs.registerOutParameter("_ID_PERSONAL_BIBLIOTECA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            practicante.setId(cs.getInt("_ID_PERSONAL_BIBLIOTECA"));
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
    public int actualizar(Practicante practicante, TipoPersonal idTipoPersonal) {
        int res=0;
        try{
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PERSONAL_BIBLIOTECA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_PERSONAL_BIBLIOTECA", practicante.getId());
            cs.setInt("_ID_BIBLIOTECA", practicante.getBiblioteca().getId());
            cs.setInt("_ID_TIPO_PERSONAL",idTipoPersonal.value);
            cs.setInt("_ACTIVO",1);
            cs.setString("_DIA_SEMANA", "");
            cs.setString("_NOMBRE", practicante.getNombre());
            cs.setString("_APELLIDO", practicante.getApellido());
            cs.setString("_EMAIL", practicante.getEmail());
            cs.setString("_PASSWORD", practicante.getContrasenia());
            cs.setString("_CODIGO", practicante.getCodigo());
            cs.setDate("_FECHA_INGRESO",  new java.sql.Date(practicante.getFecha_ingreso().getTime()));
            cs.setBytes("_FOTO", practicante.getFoto());
            cs.setInt("_ID_TURNO", practicante.getTurno().getId());
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
    public void eliminar(int idPracticante) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PRACTICANTE(?)}");
            cs.setInt("_ID", idPracticante);
            
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
    public ArrayList<Practicante> listar(String nombre, String apellido) {
        ArrayList<Practicante> practicantes = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_PERSONAL_BIBLIOTECA_ACTIVO(?,?,?)}");
            cStmt.setString("_NOMBRE", nombre);
            cStmt.setString("_APELLIDO", apellido);
            cStmt.setInt("_ID_TIPO_PERSONAL", TipoPersonal.Practicante.value);
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Practicante e = new Practicante();
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
                practicantes.add(e);
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
        return practicantes;
    }

}
