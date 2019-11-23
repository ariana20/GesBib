package pe.edu.pucp.gesbibsoft.dao;

import pe.edu.pucp.gesbibsoft.model.Biblioteca;
import java.util.ArrayList;

public interface BibliotecaDAO {

    public int insertar(Biblioteca biblioteca);

    public int actualizar(Biblioteca biblioteca);

    public int eliminar(int idBiblioteca);

    public ArrayList<Biblioteca> listar();
    
    public ArrayList<Biblioteca> listar_por_nombre(String nombre);

}
