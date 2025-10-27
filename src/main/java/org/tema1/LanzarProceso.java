package org.tema1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanzarProceso {
    public static void main(String[] args) {
        LanzarProceso proceso = new LanzarProceso();

        String ruta = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        proceso. ejecutar(ruta);
        System.out.println("Proceso finalizado");

        proceso.ejecutarSuma(1, 2);
        System.out.println("OK");
    }

    public void ejecutar(String ruta) {
        ProcessBuilder pb;

        try {
            pb = new ProcessBuilder(ruta);
            pb.start();
        } catch (IOException e) {
            System.out.println("Error en proceso de la ruta." + e.getMessage());
        }
    }

    public void ejecutarSuma(Integer num1, Integer num2) {
        String clase = "org.tema1.Contador";
        ProcessBuilder pb;

        try {
            pb = new ProcessBuilder("java",
                    "-cp",
                    "build/classes/java/main",
                    clase,
                    num1.toString(),
                    num2.toString());
            pb.inheritIO();
            pb.start();
        }  catch (IOException e) {
            Logger.getLogger(LanzarProceso.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}