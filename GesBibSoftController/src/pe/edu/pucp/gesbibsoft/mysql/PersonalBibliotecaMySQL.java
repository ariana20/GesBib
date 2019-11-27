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
import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.config.DBManager;
import pe.edu.pucp.gesbibsoft.dao.PersonalBibliotecaDAO;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Auxiliar;
import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import pe.edu.pucp.gesbibsoft.model.Bibliotecario;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.PersonalBiblioteca;
import pe.edu.pucp.gesbibsoft.model.Practicante;

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
    
    
    
    @Override
    public ArrayList<PersonalBiblioteca> listar(String nombre, String apellido) {
        ArrayList<PersonalBiblioteca> personal = new ArrayList<>();
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
                e.setDiaSemana(rs.getString("DIA_SEMANA"));
        
                personal.add(e);
            }
            
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cStmt = con.prepareCall("{call LISTAR_PERSONAL_BIBLIOTECA_ACTIVO(?,?,?)}");
            cStmt.setString("_NOMBRE", nombre);
            cStmt.setString("_APELLIDO", apellido);
            cStmt.setInt("_ID_TIPO_PERSONAL", TipoPersonal.Auxiliar.value);
            rs=cStmt.executeQuery();
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
                personal.add(e);
            }
            
            
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cStmt = con.prepareCall("{call LISTAR_PERSONAL_BIBLIOTECA_ACTIVO(?,?,?)}");
            cStmt.setString("_NOMBRE", nombre);
            cStmt.setString("_APELLIDO", apellido);
            cStmt.setInt("_ID_TIPO_PERSONAL", TipoPersonal.Practicante.value);
            rs=cStmt.executeQuery();
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
                personal.add(e);
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
        return personal;
    }

    @Override
    public int eliminar(int idPersonal) {
        int resul=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PERSONAL_BIBLIOTECA(?)}");
            cs.setInt("_ID_PERSONAL_BIBLIOTECA", idPersonal);
            
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
    public PersonalBiblioteca informacionPersonalBiblioteca(String correo) {
        PersonalBiblioteca perBib = null;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call GET_INFORMACION_PERSONAL_BIBLIOTECA(?)}");
            cs.setString("_EMAIL", correo);
            ResultSet rs = cs.executeQuery();
            if(rs.next()) {
                perBib = new PersonalBiblioteca();
                perBib.setId(rs.getInt("ID_USUARIO"));
                perBib.setNombre(rs.getString("NOMBRE"));
                perBib.setApellido(rs.getString("APELLIDO"));
                perBib.setEmail(rs.getString("EMAIL"));
                perBib.setCodigo(rs.getString("CODIGO"));
                perBib.setFecha_ingreso(rs.getDate("FECHA_INGRESO"));
                perBib.setTotalHorasExtra(rs.getFloat("TOTAL_HORA_EXTRA"));
                perBib.setFoto(rs.getBytes("FOTO"));
                Biblioteca bib = new Biblioteca(rs.getString("NOMBRE_BIBLIOTECA"));
                perBib.setBiblioteca(bib);
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
        return perBib;
    }
    
}
