package pe.edu.pucp.gesbibsoft.model;

public class Aviso {
    private int id;
    private int activo;

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    private String titulo;
    private String descripcion;
    private Gestor gestor;

    // GETTERS Y SETTERS
    public Gestor getGestor() {
        return gestor;
    }
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}