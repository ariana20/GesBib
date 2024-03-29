/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.gesbibsoft.model.PerfilExperiencia;
import pe.edu.pucp.gesbibsoft.model.PersonalBiblioteca;

/**
 *
 * @author Ariana
 */
public interface PersonalBibliotecaDAO {
    
    int asignarPerfil(PersonalBiblioteca personalBiblioteca,PerfilExperiencia perfilExperiencia);
    ArrayList<PersonalBiblioteca> listar(String nombre, String apellido);
    ArrayList<PersonalBiblioteca> listarPorPerfilExperiencia (int idPerfilExperiencia);
    public int eliminar(int idPersonal);
    public PersonalBiblioteca informacionPersonalBiblioteca(String correo);
    ArrayList<PersonalBiblioteca> listar_todo_sobre_personal(String nombre);
}
