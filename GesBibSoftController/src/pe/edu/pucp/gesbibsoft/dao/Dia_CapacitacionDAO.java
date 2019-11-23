package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.Dia_Capacitacion;

public interface Dia_CapacitacionDAO {
    void insertar(Dia_Capacitacion dia_capa);

    int actualizar(Dia_Capacitacion dia_capa);

    int eliminar(int idDia_capa);

    ArrayList<Dia_Capacitacion> listar(int id_capacitacion);
}
