/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocompiladores;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class PROYECTOcOMPILADORES {

    /**
     * @param args the command line arguments
     */
     public final static int GENERAR = 1;
    public final static int EJECUTAR = 2;
    public final static int SALIR = 3;
    
    
    public static void main(String[] args) throws IOException {
//        String path = "C:/Users/USER/Documents/NetBeansProjects/PROYECTOcOMPILADORES/src/proyectocompiladores/Lexico.flex";
//        generarLexico(path);
//        interfaz ventana = new interfaz();
//        ventana.setVisible(true);
//       
    java.util.Scanner in = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("Elija una opcion: ");
            System.out.println("1) Generar");
            System.out.println("2) Ejecutar");
            System.out.println("3) Salir");
            System.out.print("Opcion: ");
            valor = in.nextInt();
            switch (valor) {
                /*  Generamos el analizador lexico y sintactico.
                 lcalc.flex contiene la definicion del analizador lexico
                 ycalc.cup contiene la definicion del analizador sintactico
                 */
                case GENERAR: {
                    System.out.println("\n*** Generando ***\n");
                    String archLexico = "";
                    String archSintactico = "";
                    if (args.length > 0) {
                        System.out.println("\n*** Procesando archivos custom ***\n");
                        archLexico = args[0];
                        archSintactico = args[1];
                    } else {
                        archLexico = "C:/Users/USER/Documents/NetBeansProjects/PROYECTOcOMPILADORES/src/proyectocompiladores/Lexico.flex";
                        archSintactico = "C:/Users/USER/Documents/NetBeansProjects/PROYECTOcOMPILADORES/src/proyectocompiladores/Sintactico.cup";
                    }
                    String[] lexico = {archLexico};
                    String[] sintactico = {"-parser", "Sintactico", archSintactico};
                    jflex.Main.main(lexico);
                    try {
                        java_cup.Main.main(sintactico);
                    } catch (Exception ex) {
                        Logger.getLogger(PROYECTOcOMPILADORES.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //movemos los archivos generados
                    boolean mvAL = moverArch("Lexico.java");
                    boolean mvAS = moverArch("Sintactico.java");
                    boolean mvSym= moverArch("sym.java");
                    if(mvAL && mvAS && mvSym){
                        System.exit(0);
                    }
                    System.out.println("Generado!");
                    break;
                }
                case EJECUTAR: {
                    /*  Ejecutamos el analizador lexico y sintactico
                     sobre un archivo de pruebas. 
                     */
                    String[] archivoPrueba = {"fichero.txt"};
                    Sintactico.main(archivoPrueba);
                    System.out.println("Ejecutado!");
                    break;
                }
                case SALIR: {
                    System.out.println("Adios!");
                    break;
                }
                default: {
                    System.out.println("Opcion no valida!");
                    break;
                }
            }
        } while (valor != 3);

    }

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "proyectocompiladores" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }

        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }
}