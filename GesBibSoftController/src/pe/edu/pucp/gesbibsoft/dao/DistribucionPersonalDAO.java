
package pe.edu.pucp.gesbibsoft.dao;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.model.DistribucionPersonal;
import pe.edu.pucp.gesbibsoft.model.PuntosAtencion;


public interface DistribucionPersonalDAO {

    void insertar(DistribucionPersonal distribuPers);

    void actualizar(DistribucionPersonal distribuPers);

    void eliminar(int idDistribuPers, int idPuntoAtencion);

    ArrayList<DistribucionPersonal> listarPorPersonal(int idPersonal, Date fechaIni, Date fechaFin);
    

    //ArrayList<PuntosAtencion> listarPorFechaHora(int idPuntoAtencion, Date fecha, Time hora_ini, Time hora_fin);

    public ArrayList<DistribucionPersonal> listarPorFechaHora(int idPuntoAtencion, Date fechaIni, Date fechaFin);


    //byTyS
    ArrayList<DistribucionPersonal> listarDistribPersonalenFecha(
            int idPersonal, Date fecha);
    

}
