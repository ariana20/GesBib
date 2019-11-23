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
import pe.edu.pucp.gesbibsoft.dao.CapacitacionDAO;
import pe.edu.pucp.gesbibsoft.model.Capacitacion;
import pe.edu.pucp.gesbibsoft.model.Dia_Capacitacion;
import pe.edu.pucp.gesbibsoft.model.Personal;

public class CapacitacionMySQL implements CapacitacionDAO {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    CallableStatement cs = null;
    Statement st = null;

    @Override
    public int insertar(Capacitacion capacitacion) {
        int result=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            
            cs = con.prepareCall("{call INSERTAR_CAPACITACION(?,?,?,?,?,?,?,?)}");
            
            cs.setString("_NOMBRE", capacitacion.getNombre());
            cs.setString("_DESCRIPCION", capacitacion.getDescripcion());
            cs.setString("_LUGAR", capacitacion.getLugar());
            cs.setDate("_FECHA_INI", new java.sql.Date(capacitacion.getFecha_ini().getTime()));
            cs.setDate("_FECHA_FIN", new java.sql.Date(capacitacion.getFecha_fin().getTime()));
            cs.setDate("_INICIO_INSCRIPCION", new java.sql.Date(capacitacion.getInicio_inscripcion().getTime()));
            cs.setDate("_FIN_INSCRIPCION", new java.sql.Date(capacitacion.getFin_inscripcion().getTime()));
            cs.registerOutParameter("_ID_CAPACITACION", java.sql.Types.INTEGER);
            
            cs.executeUpdate();
            
            capacitacion.setId(cs.getInt("_ID_CAPACITACION"));
            
            // INSERTANDO TODOS LOS DIAS DE LA CAPACITACION EN LA INSTANCIA DE CAPACITACION
            if (capacitacion.getListaDiasCapacitacion() != null) {
                for(Dia_Capacitacion dia_capa : capacitacion.getListaDiasCapacitacion()) {
                    CallableStatement cs2 = con.prepareCall("{call INSERTAR_DIA_CAPACITACION(?,?,?,?,?,?)}");
                    
                    cs2.setInt("_ID_CAPACITACION", dia_capa.getCapacitacion().getId());
                    cs2.setTime("_HORA_INI", new java.sql.Time(dia_capa.getHora_ini().getTime()));
                    cs2.setTime("_HORA_FIN", new java.sql.Time(dia_capa.getHora_fin().getTime()));
                    cs2.setDate("_FECHA", new java.sql.Date(dia_capa.getFecha().getTime()));
                    cs2.setInt("_ACTIVO", 1);

                    cs2.registerOutParameter("_ID_DIA_CAPACITACION", java.sql.Types.INTEGER);

                    cs2.executeUpdate();
                    dia_capa.setIdDiaCapacitacion(cs2.getInt("_ID_DIA_CAPACITACION"));
                }
            }
            result=1;

        } catch ( SQLException ex) {
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
    public int actualizar(Capacitacion capacitacion) {
        int resultado=0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_CAPACITACION(?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_CAPACITACION", capacitacion.getId());
            cs.setString("_NOMBRE", capacitacion.getNombre());
            cs.setString("_DESCRIPCION", capacitacion.getDescripcion());
            cs.setString("_LUGAR", capacitacion.getLugar());
            
            java.sql.Date date1 = new java.sql.Date(capacitacion.getFecha_ini().getTime());
            java.sql.Date date2 = new java.sql.Date(capacitacion.getFecha_fin().getTime());
            java.sql.Date date3 = new java.sql.Date(capacitacion.getInicio_inscripcion().getTime());
            java.sql.Date date4 = new java.sql.Date(capacitacion.getFin_inscripcion().getTime());
            
            cs.setDate("_FECHA_INI", date1);
            cs.setDate("_FECHA_FIN", date2);
            cs.setDate("_INICIO_INSCRIPCION", date3);
            cs.setDate("_FIN_INSCRIPCION", date4);
            
            // UPDATE DE TODOS LOS DIAS DE LA CAPACITACION
            if (capacitacion.getListaDiasCapacitacion() != null) {
                for (Dia_Capacitacion dia_capa : capacitacion.getListaDiasCapacitacion()) {
                    CallableStatement cs2 = con.prepareCall("{call ACTUALIZAR_DIA_CAPACITACION(?,?,?,?,?,?)}");
    
                    cs2.setInt("_ID_DIA_CAPACITACION", dia_capa.getIdDiaCapacitacion());
                    cs2.setInt("_ID_CAPACITACION", dia_capa.getCapacitacion().getId());
                    java.sql.Time time1 = new java.sql.Time(dia_capa.getHora_ini().getTime());
                    java.sql.Time time2 = new java.sql.Time(dia_capa.getHora_fin().getTime());
                    java.sql.Date date_x = new java.sql.Date(dia_capa.getFecha().getTime());

                    cs2.setTime("_HORA_INI", time1);
                    cs2.setTime("_HORA_FIN", time2);
                    cs2.setDate("_FECHA", date_x);
                    //cs2.setInt("_ACTIVO", dia_capa.getActivo());

                    cs2.executeUpdate();
                }
            }
            
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
    public int eliminar(int idCapacitacion) {
        int resultado =0;
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            
            // ELIMINANDO A LOS DIAS CAPACITACION
            CallableStatement cs2 = con.prepareCall("{call ELIMINAR_DIA_CAPACITACION_TOTAL(?)}");
            cs2.setInt("_ID_CAPACITACION", idCapacitacion);
            cs2.executeUpdate();
            
            //ELIMINANDO LA CAPACITACION
            cs = con.prepareCall("{call ELIMINAR_CAPACITACION(?)}");
            cs.setInt("_ID_CAPACITACION", idCapacitacion);
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
    public ArrayList<Capacitacion> listar() {
        ArrayList<Capacitacion> capacitaciones = new ArrayList<>();
        try {
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_CAPACITACIONES()}");
            
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                Capacitacion capa = new Capacitacion();
                
                capa.setId(rs.getInt("ID_CAPACITACION"));
                capa.setNombre(rs.getString("NOMBRE"));
                capa.setLugar(rs.getString("LUGAR"));
                capa.setDescripcion(rs.getString("DESCRIPCION"));
                capa.setFecha_ini(rs.getDate("FECHA_INICIO"));
                capa.setFecha_fin(rs.getDate("FECHA_FIN"));
                capa.setInicio_inscripcion(rs.getDate("INICIO_INSCRIPCION"));
                capa.setFin_inscripcion(rs.getDate("FIN_INSCRIPCION"));
                
                // AÑADIENDO TODOS LOS DIAS DE LA CAPACITACION
                ArrayList<Dia_Capacitacion> dias_capacitacion = new ArrayList<>();
               
                CallableStatement cs2 = con.prepareCall("{call LISTAR_DIAS_CAPACITACION(?)}");
                cs2.setInt("_ID_CAPACITACION", capa.getId());
                ResultSet rs2 = cs2.executeQuery();
                
                while(rs2.next()) {
                    Dia_Capacitacion dia_capa = new Dia_Capacitacion();
                    dia_capa.setIdDiaCapacitacion(rs2.getInt("ID_DIA_CAPACITACION"));
                    dia_capa.setHora_ini(rs2.getTime("HORA_INICIO"));
                    System.out.println(rs2.getTime("HORA_INICIO"));
                    dia_capa.setHora_fin(rs2.getTime("HORA_FIN"));
                    dia_capa.setFecha(rs2.getDate("FECHA"));
                    dia_capa.setActivo(rs2.getInt("ACTIVO"));
                    //dia_capa.setCapacitacion(capa);
                    
                    dias_capacitacion.add(dia_capa);
                }
                capa.setListaDiasCapacitacion(dias_capacitacion);
                
                //AÑADIENDO TODO EL PERSONAL QUE ASISTIRA A LA CAPACITACION
                ArrayList<Personal> asistentes = new ArrayList<>();
               
                CallableStatement cs3 = con.prepareCall("{call LISTAR_ASISTENTES_CAPACITACION(?)}");
                cs3.setInt("_ID_CAPACITACION", capa.getId());
                ResultSet rs3 = cs3.executeQuery();
                
                while(rs3.next()) {
                    Personal asistente = new Personal();
                    asistente.setId(rs3.getInt("ID_USUARIO"));
                    asistente.setNombre(rs3.getString("NOMBRE"));
                    asistente.setApellido(rs3.getString("APELLIDO"));
                    asistente.setCodigo(rs3.getString("CODIGO"));
                    asistente.setEmail(rs3.getString("EMAIL"));
                    
                    asistentes.add(asistente);
                }
                capa.setListaPersonal(asistentes);
                
                capacitaciones.add(capa);
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
        return capacitaciones;
    }

}
