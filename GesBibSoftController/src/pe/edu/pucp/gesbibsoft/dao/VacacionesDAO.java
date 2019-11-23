/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.Vacaciones;

/**
 *
 * @author alulab14
 */
public interface VacacionesDAO {
    
    void insertar(Vacaciones vacaciones);

    void actualizar(Vacaciones vacaciones);

    void eliminar(int idVacaciones);

    ArrayList<Vacaciones> listar();
}
