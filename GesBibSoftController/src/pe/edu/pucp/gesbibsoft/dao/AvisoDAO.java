
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.Aviso;


public interface AvisoDAO {
    
    void insertar(Aviso aviso);

    void actualizar(Aviso aviso);

    void eliminar(int idAviso);

    ArrayList<Aviso> listar();
    
    
}
