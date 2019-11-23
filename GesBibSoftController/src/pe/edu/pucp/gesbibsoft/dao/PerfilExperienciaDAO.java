
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;

public interface PerfilExperienciaDAO {
    int insertar(PerfilExperiencia perfilExperiencia);

    void actualizar(PerfilExperiencia perfilExperiencia);

    void eliminar(int idPerfilExperiencia);

    ArrayList<PerfilExperiencia> listar();
}
