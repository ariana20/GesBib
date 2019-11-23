/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gesbibsoft.model.Personal;
import pe.edu.pucp.gesbibsoft.pair.PairStringUsuario;

/**
 *
 * @author alulab14
 */
public interface UsuarioDAO {
    PairStringUsuario validarUsuario(String email, String password);
    ArrayList<Personal> listarUsuariosLibres(Date fecha, Time hora_inicio, Time hora_fin, String nombre_perfil);
}
