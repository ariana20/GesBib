package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.AvisoDAO;
import pe.edu.pucp.gesbibsoft.model.Aviso;
import pe.edu.pucp.gesbibsoft.model.Gestor;

public class AvisoMySQL implements AvisoDAO {
    Connection con = null;
    CallableStatement cs = null;

    @Override
    public void insertar(Aviso aviso) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_AVISO(?,?,?,?)}");
            cs.setInt("_ID_GESTOR",aviso.getGestor().getId() );
            cs.setString("_TITULO", aviso.getTitulo());
            cs.setString("_DESCRIPCION", aviso.getDescripcion());
            
            cs.registerOutParameter("_ID_AVISO", java.sql.Types.INTEGER);
            cs.executeUpdate();
            aviso.setId(cs.getInt("_ID_AVISO"));
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    
    }

    @Override
    public void actualizar(Aviso aviso) {
        try{
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_AVISO(?,?,?,?)}");
            cs.setInt("_ID_AVISO", aviso.getId());
            cs.setInt("_ACTIVO", aviso.getActivo());
            cs.setString("_TITULO", aviso.getTitulo());
            cs.setString("_DESCRIPCION", aviso.getDescripcion());
            cs.setInt("_ID_GESTOR", aviso.getGestor().getId());
            
            cs.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void eliminar(int idAviso) {
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_AVISO(?)}");
            cs.setInt("_ID_AVISO", idAviso);
            
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
    public ArrayList<Aviso> listar() {
        ArrayList<Aviso> avisos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            CallableStatement cStmt = con.prepareCall("{call LISTAR_AVISOS()}");
            ResultSet rs=cStmt.executeQuery();
            while(rs.next()){
                Aviso  e = new Aviso();
                e.setId(rs.getInt("ID_AVISO"));
                e.setTitulo(rs.getString("TITULO"));
                e.setDescripcion(rs.getString("DESCRIPCION"));
                Gestor ges=new Gestor();
                e.setGestor(ges);
                e.getGestor().setId(rs.getInt("ID_GESTOR"));
                e.setActivo(rs.getInt("ACTIVO"));
                avisos.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return avisos;
    }

}
