/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.TipoInasistencia;

public interface TipoInasistenciaDAO {
    void insertar(TipoInasistencia tipoInasistencia);

    void actualizar(TipoInasistencia tipoInasistencia);

    void eliminar(int idTipoInasistencia);

    ArrayList<TipoInasistencia> listar();
}
