package pe.edu.pucp.gesbibsoft.model;

import pe.edu.pucp.gesbibsoft.enums.DiaSemana;



import java.util.Date;

public class Bibliotecario extends PersonalBiblioteca {

    //ATRIBUTOS PROPIOS
    private String diaSemana;

    //CONSTRUCTORES
    public Bibliotecario() {
    }

    public Bibliotecario(String nombre, String apellido, String codigo, String email, String contrasenia, Date fechaIngreso, float totalHorasExtra, String diaSemana) {
        super(nombre, apellido, codigo, email, contrasenia,fechaIngreso, totalHorasExtra );
        this.diaSemana=diaSemana;
    }
    


    //SETS AND GETS

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }


}
