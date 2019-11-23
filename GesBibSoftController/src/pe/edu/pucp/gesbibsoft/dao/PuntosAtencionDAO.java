/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;


public interface PuntosAtencionDAO {
    int insertar(PuntosAtencion puntoAt);

    int actualizar(PuntosAtencion puntoAt);

    void eliminar(int idPuntosAt);

    ArrayList<PuntosAtencion> listar(int idBiblioteca);

}
