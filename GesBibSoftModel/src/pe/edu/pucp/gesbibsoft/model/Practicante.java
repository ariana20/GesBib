package pe.edu.pucp.gesbibsoft.model;


import java.util.Date;

public class Practicante extends PersonalBiblioteca {

    public Practicante() {
    }

    public Practicante(String nombre, String apellido, String codigo, String email, String contrasenia, Date fechaIngreso, float totalHorasExtra) {
        super(nombre, apellido, codigo, email, contrasenia,fechaIngreso, totalHorasExtra );
    }


}
