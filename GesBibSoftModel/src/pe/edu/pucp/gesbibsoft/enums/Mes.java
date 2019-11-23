package pe.edu.pucp.gesbibsoft.enums;

public enum Mes {
    ENERO("ENERO"),
    FEBRERO("FEBRERO"),
    MARZO("MARZO"),
    ABRIL("ABRIL"),
    MAYO("MAYO"),
    JUNIO("JUNIO"),
    JULIO("JULIO"),
    AGOSTO("AGOSTO"),
    SEPTIEMBRE("SEPTIEMBRE"),
    OCTUBRE("OCTUBRE"),
    NOVIEMBRE("NOVIEMBRE"),
    DICIEMBRE("DICIEMBRE");
    public final String value;
    private Mes(String value){
        this.value = value;
    }
}
