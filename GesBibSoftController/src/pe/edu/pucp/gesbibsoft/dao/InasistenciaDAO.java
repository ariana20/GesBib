
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.model.Inasistencia;


public interface InasistenciaDAO {
    int insertar(Inasistencia inasistencia);

    int actualizar(Inasistencia inasistencia);

    void eliminar(int idInasistencia);

    ArrayList<Inasistencia> listarPorPersonal(int idPresonal);
    
    public ArrayList<Inasistencia> listarInasistencias(int idPersonal);
    public ArrayList<Inasistencia> listarInasistenciasPorFecha(int idPersonal, Date fecha);
    //by tys
    ArrayList<Inasistencia> listarHorasLibresDePersonal(int idPersonal, int justificado);
    ArrayList<Inasistencia> listarHorasLibresDePersonalPorFecha(
                                        int idPersonal, int justificado, Date fecha);

    public ArrayList<Inasistencia> listarTodasHorasLibresDePersonal(int idPersonal);
}
