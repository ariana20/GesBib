
package pe.edu.pucp.gesbibsoft.enums;

public enum TipoPersonal {
    Bibliotecario(1),
    Auxiliar(2),
    Practicante(3);
    public final int value;
    private TipoPersonal(int value){
        this.value = value;
    }
}
