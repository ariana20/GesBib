package pe.edu.pucp.gesbibsoft.model;

import java.util.*;

public class PuntosAtencion {
    // ATRIBUTOS PROPIOS
    private int id;
    private int piso;
    private String nombre;
    private int cant_min_pers;
    private int cant_opt_pers;
    
    // ATRIBUTOS ASIOCIACIONES
    private Biblioteca biblioteca;
    private PerfilExperiencia perfilExperiencia;
    private ArrayList<DistribucionPersonal> listaDistribuciones;
    private ArrayList<PerfilExperiencia> listaPerfiles;

    //CONSTRUCTORES
    public PuntosAtencion() {
        this.listaDistribuciones = new ArrayList<>();
        this.listaPerfiles = new ArrayList<>();
    }

    public PuntosAtencion(int piso, String nombre, int cant_min_pers, int cant_opt_pers) {
        this.piso = piso;
        this.nombre = nombre;
        this.cant_min_pers = cant_min_pers;
        this.cant_opt_pers = cant_opt_pers;
        this.listaDistribuciones = new ArrayList<>();
        this.listaPerfiles = new ArrayList<>();
    }

    public PerfilExperiencia getPerfilExperiencia() {
        return perfilExperiencia;
    }

    //SETS AND GETS
    public void setPerfilExperiencia(PerfilExperiencia perfilExperiencia) {
        this.perfilExperiencia = perfilExperiencia;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cant_min_pers
     */
    public int getCant_min_pers() {
        return cant_min_pers;
    }

    /**
     * @param cant_min_pers the cant_min_pers to set
     */
    public void setCant_min_pers(int cant_min_pers) {
        this.cant_min_pers = cant_min_pers;
    }

    /**
     * @return the cant_opt_pers
     */
    public int getCant_opt_pers() {
        return cant_opt_pers;
    }

    /**
     * @param cant_opt_pers the cant_opt_pers to set
     */
    public void setCant_opt_pers(int cant_opt_pers) {
        this.cant_opt_pers = cant_opt_pers;
    }

    /**
     * @return the biblioteca
     */
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the listaDistribuciones
     */
    public ArrayList<DistribucionPersonal> getListaDistribuciones() {
        return listaDistribuciones;
    }

    /**
     * @return the listaPerfiles
     */
    public ArrayList<PerfilExperiencia> getListaPerfiles() {
        return listaPerfiles;
    }
   
    public void addPerfil(PerfilExperiencia perfil) {
        this.listaPerfiles.add(perfil);
    }
    public void removePerfil(PerfilExperiencia perfil) {
        this.listaPerfiles.remove(perfil);
    }
    public void addDistribucion(DistribucionPersonal distribucion) {
        this.listaDistribuciones.add(distribucion);
    }
    public void removeDistribucion(DistribucionPersonal distribucion) {
        this.listaDistribuciones.remove(distribucion);
    }
    
}
