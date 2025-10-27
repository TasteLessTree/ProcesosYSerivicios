package org.tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HacerUnPing {
    public static void main(String[] args) {
        // Crea el proceso con el comando
        ProcessBuilder pb =  new ProcessBuilder();
        pb.command("ping", "-n", "1", "www.google.com");

        try {
            System.out.println("Iniciando proceso");
            Process process = pb.start(); // Inicia el proceso

            // Crea el BufferedReader y guarda el output del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Lee el output del proceso
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Proceso finalizado con: " + exitCode);
        } catch (IOException e) {
            System.out.println("Error al iniciando proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error al obtener el código de salida proceso: " + e.getMessage());
        }
    }
}