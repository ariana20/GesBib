/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.gesbibsoft.pair;

import pe.edu.pucp.gesbibsoft.model.Usuario;

/**
 *
 * @author alulab14
 */
public class PairStringUsuario {
    String nombreTipo;
    Usuario user;

    public PairStringUsuario(String nombreTipo, Usuario user) {
        this.nombreTipo = nombreTipo;
        this.user = user;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    
}
