/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.Turno;

/**
 *
 * @author alulab14
 */
public interface TurnoDAO {

    void insertar(Turno turno);

    void actualizar(Turno turno);

    void eliminar(int idTurno);

    ArrayList<Turno> listar();

}
