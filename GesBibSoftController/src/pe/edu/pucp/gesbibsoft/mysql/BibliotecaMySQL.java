package pe.edu.pucp.gesbibsoft.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.BibliotecaDAO;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Gestor;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;

public class BibliotecaMySQL implements BibliotecaDAO {

    Connection con = null;
    CallableStatement cs = null;
    CallableStatement cStmt=null;
    Statement st = null;

    @Override
    public int insertar(Biblioteca biblioteca) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);

            cs = con.prepareCall("{call INSERTAR_BIBLIOTECA(?,?,?)}");
            cs.setString("_NOMBRE", biblioteca.getNombre());
            if(biblioteca.getGestor()!=null)
                cs.setInt("_ID_GESTOR",biblioteca.getGestor().getId());
            cs.registerOutParameter("_ID_BIBLIOTECA", java.sql.Types.INTEGER);
            cs.executeUpdate();
            biblioteca.setId(cs.getInt("_ID_BIBLIOTECA"));
            
            for(PuntosAtencion pa : biblioteca.getListaPuntosAtencion()){
                
                cs = con.prepareCall("{call INSERTAR_PUNTO_ATENCION(?,?,?,?,?,?,?)}");
                cs.setInt("_PISO", pa.getPiso());
                cs.setString("_NOMBRE", pa.getNombre());
                cs.setInt("_CANTIDAD_MIN_PERSONAL", pa.getCant_min_pers());
                cs.setInt("_CANTIDAD_OPT_PERSONAL", pa.getCant_opt_pers());
                cs.setInt("_ID_BIBLIOTECA", biblioteca.getId());
                cs.setInt("_ID_PERFIL_EXPERIENCIA", pa.getPerfilExperiencia().getId());
                cs.registerOutParameter("_ID_PUNTO_ATENCION", java.sql.Types.INTEGER);
                cs.executeUpdate();
                pa.setId(cs.getInt("_ID_PUNTO_ATENCION"));
                
            }
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
    public int actualizar(Biblioteca biblioteca) {
        int result=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);

            cs = con.prepareCall("{call ACTUALIZAR_BIBLIOTECA(?,?,?)}");
            cs.setInt("_ID_BIBLIOTECA", biblioteca.getId());
            cs.setString("_NOMBRE", biblioteca.getNombre());
            cs.setInt("_ID_GESTOR",biblioteca.getGestor().getId());
            cs.setInt("_ACTIVO", 1);
            cs.executeUpdate();
            //eliminé a los antiguos, ahora inserto a los nuevos
            for(PuntosAtencion pa: biblioteca.getListaPuntosAtencion()){
                cs = con.prepareCall("{call INSERTAR_PUNTO_ATENCION(?,?,?,?,?,?,?,?)}");
                cs.setInt("_ID_PUNTO_ATENCION", pa.getId());
                cs.setInt("_PISO",pa.getPiso());
                cs.setString("_NOMBRE", pa.getNombre());
                cs.setInt("_CANTIDAD_MIN_PERSONAL",pa.getCant_min_pers());
                cs.setInt("_CANTIDAD_OPT_PERSONAL",pa.getCant_opt_pers());
                cs.setInt("_ID_BIBLIOTECA",biblioteca.getId());
                cs.setInt("_ID_PERFIL_EXPERIENCIA",pa.getPerfilExperiencia().getId());
                cs.registerOutParameter("_ID_PUNTO_ATENCION", java.sql.Types.INTEGER);
                cs.executeUpdate();
                pa.setId(cs.getInt("_ID_PUNTO_ATENCION"));
            }
            result=1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public int eliminar(int idBiblioteca) {
        int res=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_BIBLIOTECA(?)}");
            cs.setInt("_ID", idBiblioteca);
            
            cs.executeUpdate();
            res=1;
        } catch ( SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return res;
    }

    @Override
    public ArrayList<Biblioteca> listar() {
        ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cStmt = con.prepareCall("{call LISTAR_BIBLIOTECAS()}");
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Biblioteca e = new Biblioteca();
                e.setId(rs.getInt("ID_BIBLIOTECA"));
                e.setNombre(rs.getString("NOMBRE"));
                bibliotecas.add(e);
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
        return bibliotecas;
    }
    
    @Override
    public ArrayList<Biblioteca> listar_por_nombre(String nombre) {
        ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cStmt = con.prepareCall("{call LISTAR_BIBLIOTECAS_POR_NOMBRE(?)}");
            cStmt.setString("_NOMBRE", nombre);
            ResultSet rs=cStmt.executeQuery();
            while (rs.next()) {
                Biblioteca e = new Biblioteca();
                e.setId(rs.getInt("ID_BIBLIOTECA"));
                e.setNombre(rs.getString("NOMBRE"));
                
                Gestor gestor = new Gestor();
                e.setGestor(gestor);
                e.getGestor().setId(rs.getInt("ID_GESTOR"));
                e.getGestor().setNombre(rs.getString("NOMBRE_G"));
                e.getGestor().setApellido(rs.getString("APELLIDO_G"));
                bibliotecas.add(e);
            }
            //AGREGADO PARA LISTAR PUNTOS DE ATENCION-----
            for(Biblioteca b : bibliotecas){
                cs = con.prepareCall("{call LISTAR_PUNTOS_ATENCION_X_BIBLIO(?)}");
                cs.setInt("_ID_BIBLIOTECA", b.getId());
                rs = cs.executeQuery();
                while(rs.next()){
                    PuntosAtencion pa = new PuntosAtencion();
                    pa.setId(rs.getInt("ID_PUNTO_ATENCION"));
                    pa.setNombre(rs.getString("NOMBRE"));
                    pa.setCant_min_pers(rs.getInt("CANTIDAD_MIN_PERSONAL"));
                    pa.setCant_opt_pers(rs.getInt("CANTIDAD_OPT_PERSONAL"));
                    pa.setPiso(rs.getInt("PISO"));
                    PerfilExperiencia perfil=new PerfilExperiencia();
                    pa.setPerfilExperiencia(perfil);
                    pa.getPerfilExperiencia().setNombrePerfil(rs.getString("NOMBRE_PERFIL"));
                    pa.getPerfilExperiencia().setId(rs.getInt("ID_PERFIL_EXPERIENCIA"));
                    
                    b.getListaPuntosAtencion().add(pa);
                }
            }
            //--------------------------------------------
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return bibliotecas;
    }

}
