
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.AdministradorSI;


public interface AdministradorSIDAO {
    
    void insertar(AdministradorSI adminSI);

    void actualizar(AdministradorSI adminSI);

    void eliminar(int idAdministradorSI);

    ArrayList<AdministradorSI> listar();
    
    
}
