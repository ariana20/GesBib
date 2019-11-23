package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.Gestor;

public interface GestorDAO {

    int insertar(Gestor gestor);

    int actualizar(Gestor gestor);

    int eliminar(int idGestor);

    ArrayList<Gestor> listar(String nombre, String apellido);
}
