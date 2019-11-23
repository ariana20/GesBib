package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.model.HoraExtra;

public interface HoraExtraDAO {

    int insertar(HoraExtra horaExtra);

    int actualizar(HoraExtra horaExtra);

    void eliminar(int idHoraExtra);

    ArrayList<HoraExtra> listar(int idPersonal);
    
    ArrayList<HoraExtra> listarPorFecha(int idPersonal, Date fecha);

}
