
package pe.edu.pucp.gesbibsoft.enums;

public enum DiaSemana {
    Lunes("Lunes"),
    Martes("Martes"),
    Miercoles("Miercoles"),
    Jueves("Jueves"),
    Viernes("Viernes"),
    Sabados("Sabados");
    public final String value;
    private DiaSemana(String value){
        this.value = value;
    }
}
