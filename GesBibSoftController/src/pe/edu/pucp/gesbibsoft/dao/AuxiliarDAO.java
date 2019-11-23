package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Auxiliar;

public interface AuxiliarDAO {

    int insertar(Auxiliar auxiliar, TipoPersonal idTipoPersonal) ;

    int actualizar(Auxiliar auxiliar, TipoPersonal idTipoPersonal);

    void eliminar(int idAuxiliar);

    ArrayList<Auxiliar> listar(String nombre, String apellido);

}
