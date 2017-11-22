/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad02_ejer01;

import generated.Alumnos;
import generated.CursoType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Carlos
 */
public class AD02_Ejer01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String path = "ejemplo_clase.xml";
        xml_engine arbol = null;

        try {

            //COGEMOS EL .XML Y CREAMOS EL OBJETO QUE CONTIENE LOS METODOS QUE VAMOS A NECESITAR
            //*********************************************************************
            //considero la raiz del programa como contenedor del archivo .xml            
            arbol = new xml_engine(path);

            
            //objeto para entrada de teclado
            Scanner entrada = new Scanner(System.in, "ISO-8859-1");//para tener ñ y acentos 
            int opcionNum = 0;

            //imprimimos las opciones del programa en consola
            System.out.println("\t:::::ADMINISTRACION DE CLASE:::::\n");

            System.out.println("\t- Elige una opción:");

            System.out.println("\t#1 Modificar profesor.");

            System.out.println("\t#2 Añadir nuevo alumno.");

            System.out.println("\t#3 Eliminar alumno.");

            System.out.println("\t#4 Imprimir fichero xml.");

            System.out.println("\t------------------------\n");

            while (opcionNum == 0) {
                try {
                    //guardamos la opcion
                    String opcion = entrada.nextLine();
                    opcionNum = Integer.parseInt(opcion);

                } catch (Exception e) {

                    System.out.println("No has introducido una opción valida.");
                    System.out.println("- Elige una opción:");
                }
            }

            CursoType curso1;

            if (opcionNum == 1 && arbol != null) {

                curso1 = arbol.getCursoType();

                String nombre;
                String asignatura;
                String especialidad;
                String curso;
                String modalidad;

                System.out.println("\n\n");

                System.out.println("Introduce el nombre del profesor:");
                nombre = entrada.nextLine();

                System.out.println("Introduce la asignatura que imparte el profesor:");
                asignatura = entrada.nextLine();

                System.out.println("Introduce la especiablidad del profesor:");
                especialidad = entrada.nextLine();

                System.out.println("Introduce el curso en el que profesor imparte clase:");
                curso = entrada.nextLine();

                System.out.println("Introduce la modalidad del curso en el que profesor imparte clase (número decimal):");
                modalidad = entrada.nextLine();

                curso1.getProfesor().setNombre(nombre);

                curso1.getProfesor().setAsignatura(asignatura);

                curso1.getProfesor().setEspecialidad(especialidad);

                curso1.getProfesor().setCurso(curso);

                curso1.getProfesor().setModalidad(new BigDecimal(modalidad));

                String arbolActualizado = arbol.setCursoType(curso1);

                PrintWriter writer = new PrintWriter(new FileWriter(path));

                //escribimos en el fichero xml
                writer.println(arbolActualizado);

                //cerramos el fichero
                writer.close();

            } else if (opcionNum == 2 && arbol != null) {

                String nombre;
                int edad;
                String direccion;
                String comentario;
                BigInteger telefono;

                System.out.println("Introduce el nombre del alumno:");
                nombre = entrada.nextLine();

                System.out.println("Introduce la edad del alumno:");
                edad = Integer.parseInt(entrada.nextLine());

                System.out.println("Introduce la dirección del alumno:");
                direccion = entrada.nextLine();

                System.out.println("Introduce un comentario sobre el alumno:");
                comentario = entrada.nextLine();

                System.out.println("Introduce el teléfono del alumno:");
                telefono = new BigInteger(entrada.nextLine());

                curso1 = arbol.getCursoType();

                //preparamos el nuevo alumno con los datos que vamos a introducirle
                Alumnos.Alumno nuevoAlumno = new Alumnos.Alumno();
                nuevoAlumno.setNombreAlumno(nombre);
                nuevoAlumno.setEdad(edad);
                nuevoAlumno.setDireccion(direccion);
                nuevoAlumno.setComentario(comentario);
                nuevoAlumno.setTelefono(telefono);

                //accedemos hasta el nivel alumno y añadimos
                curso1.getAlumnos().getAlumno().add(nuevoAlumno);
                String arbolActualizado = arbol.setCursoType(curso1);

                PrintWriter writer = new PrintWriter(new FileWriter(path));

                //Escribimos todo el árbol en el fichero
                writer.println(arbolActualizado);
                //Cerramos el fichero
                writer.close();

            } else if (opcionNum == 3 && arbol != null) {

                String nombre;

                curso1 = arbol.getCursoType();

                System.out.println("Introduce del alumno a borrar:");
                nombre = entrada.nextLine();

                for (Alumnos.Alumno alu : curso1.getAlumnos().getAlumno()) {

                    if (alu.getNombreAlumno().equals(nombre)) {

                        curso1.getAlumnos().getAlumno().remove(alu);

                    }

                }

                String arbolActualizado = arbol.setCursoType(curso1);

                PrintWriter writer = new PrintWriter(new FileWriter(path));

                //Escribimos todo el árbol en el fichero
                writer.println(arbolActualizado);
                //Cerramos el fichero
                writer.close();

            } else if (opcionNum == 4 && arbol != null) {

                BufferedReader br = new BufferedReader(new FileReader(path));

                String lineaPuntero;
                while ((lineaPuntero = br.readLine()) != null) {

                    System.out.println(lineaPuntero);

                }

            } else {

                System.out.println("No has seleccionado ninguna opción.");

            }

            //tendremos que capturar el throws JAXBException que pusimos en la clase 
        } catch (JAXBException e) {

            System.err.println(e.getMessage());

        } catch (FileNotFoundException e) {

            System.err.println(e.getMessage());

        } catch (IOException e) {

            System.err.println(e.getMessage());

        }
    }

}
