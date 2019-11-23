package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Practicante;

public interface PracticanteDAO {

    int insertar(Practicante practicante, TipoPersonal idTipoPersonal);

    int actualizar(Practicante practicante, TipoPersonal idTipoPersonal);

    void eliminar(int idPracticante);

    ArrayList<Practicante> listar(String nombre, String apellido);

}
