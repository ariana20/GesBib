
package gesbibsoft;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @authores =====>  ariana, tys, silvia, renzo, daniel
 */

public class GesBibSoft {

  
    public static void main(String[] args) throws ParseException {
        //Linea ->
        
        //PRUEBA 
        
//        AdministradorSI admin=new AdministradorSI("Maite", "Quispe", "20160242","maite.quispep@pucp.edu.pe", "jungkook");
//        DBController.insertarAdminSI(admin);




//          ArrayList<Capacitacion> lC = DBController.listarCapacitacion();
//          for(Capacitacion c: lC){
//              System.out.println(c.getId());
//              System.out.println(c.getDescripcion());
//              System.out.println(c.getFecha_fin());
//              System.out.println(c.getFecha_ini());
//              
//              ArrayList<Dia_Capacitacion> diaC=c.getListaDiasCapacitacion();
//              for(Dia_Capacitacion dC: diaC) {
//                  System.out.println("Dia: "+ dC.getIdDiaCapacitacion());
//                  System.out.println("Hora Inicio " + dC.getHora_ini());
//                  System.out.println("Hora Fin " + dC.getHora_fin());
//                  System.out.println("Fecha: " + dC.getFecha());
//              }
//          }

//        Capacitacion capacitacion = new Capacitacion("Nueva coleccion 2019", "N205", "Colecciones importadas de Europa", LocalDate.parse("2019-07-20"),LocalDate.parse("2019-07-20"), LocalDate.parse("2019-06-01"),LocalDate.parse("2019-07-19"));
//        
//        Dia_Capacitacion dia_capa=new Dia_Capacitacion(LocalTime.parse("14:00:00"), LocalTime.parse("20:00:00"), LocalDate.parse("2019-07-20"));
//        capacitacion.addDiaCapacitacion(dia_capa);
//        DBController.insertarCapacitacion(capacitacion);

//        PRUEBA LAB08
//        Gestor g =new Gestor("Kevin", "Alvarez", "152346", "kalvarez@pucp.pe", "7853798aaalk", LocalDate.parse("2018-12-12"), 15.5f);
//        DBController.insertarGestor(g);

//        PerfilExperiencia pe=new PerfilExperiencia("Atención Informes");
//        DBController.insertarPerfilExperiencia(pe);
//        
//        Biblioteca b=new Biblioteca("LUIS JAIME CISNEROS");
//        PuntosAtencion pa1= new PuntosAtencion(2, "Informes", 1, 1);
//        pa1.setPerfilExperiencia(pe);
//        b.addPuntoAtencion(pa1);
//        DBController.insertarBiblioteca(b);
        
//        Biblioteca b=new Biblioteca("SOCIALES");
//        DBController.insertarBiblioteca(b);
        
//        Auxiliar aux=new Auxiliar("Jimena", "Porras", "123456", "jimena@pucp.edu.pe","jimina", LocalDate.parse("2019-12-15"), 30.5f );
//        aux.setBiblioteca(b);
//        DBController.insertarAuxiliar(aux);
//        System.out.println("1");
        
//        Practicante pract=new Practicante("Rodrigo", "Quispe", "875621", "rquispe@pucp.edu.pe","valerie", LocalDate.parse("2019-05-15"), 5.5f );
//        pract.setBiblioteca(b);
//        DBController.insertarPracticante(pract);
//        System.out.println("11");
//        
//        Practicante pract2=new Practicante("Camila", "Corrado", "875921", "ccorrado@pucp.edu.pe","valerie", LocalDate.parse("2016-05-15"), 6.5f );
//        pract2.setBiblioteca(b);
//        DBController.insertarPracticante(pract2);
//        System.out.println("22");
        
//        Bibliotecario bib=new Bibliotecario("Ramiro", "Castro", "582611", "rcastro@pucp.edu.pe","caramba", LocalDate.parse("2015-05-15"), 84.5f, DiaSemana.Martes);
//        bib.setBiblioteca(b);
//        DBController.insertarBibliotecario(bib);
//        System.out.println("111");
        
        String myTime = "10:30:54";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long ms = sdf.parse(myTime).getTime();
        Time t = new Time(ms);
        System.out.println(t);
    }
    
}
