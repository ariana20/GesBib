
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.enums.TipoPersonal;
import pe.edu.pucp.gesbibsoft.model.Bibliotecario;


public interface BibliotecarioDAO {

    int insertar(Bibliotecario bibliotecario, TipoPersonal idTipoPersonal);

    int actualizar(Bibliotecario bibliotecario, TipoPersonal idTipoPersonal);

    void eliminar(int idBibliotecario);

    ArrayList<Bibliotecario> listar(String nombre, String apellido);

}
