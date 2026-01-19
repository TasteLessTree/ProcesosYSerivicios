package org.temario.tema1;

import java.io.*;

public class GuardarOutputFichero {
    public static void main(String[] args) {
        // Crea el proceso con el comando
        ProcessBuilder pb =  new ProcessBuilder();
        pb.command("ping", "-n", "1", "www.google.com");

        // Guardar el output en un fichero
        File output = new File("output/ejemplo3.txt");
        pb.redirectOutput(output);

        try {
            System.out.println("Iniciando proceso");
            Process process = pb.start(); // Inicia el proceso

            int exitCode = process.waitFor();
            System.out.println("Proceso finalizado con: " + exitCode);
        } catch (IOException e) {
            System.out.println("Error al iniciando proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error al obtener el código de salida proceso: " + e.getMessage());
        }

        // Vamos a leer el fichero :)
        try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer fichero: " + e.getMessage());
        }
    }
}