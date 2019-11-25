/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.Capacitacion;


public interface CapacitacionDAO {

    int insertar(Capacitacion capacitacion);

    int actualizar(Capacitacion capacitacion);

    int eliminar(int idCapacitacion);

    ArrayList<Capacitacion> listar();
    
    //bytys
    //ArrayList<Capacitacion> listarCapacitacionesDePersonalxEstado(int,int,int);
    ArrayList <Capacitacion> listarCapacitacionesDePersonalxEstado(
            int idPersonal,int estado);
    
}
