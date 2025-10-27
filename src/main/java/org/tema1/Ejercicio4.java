package org.tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio4 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("dir");

        try {
            Process process = processBuilder.start();

            // Leer y guardar la salida del proceso
            BufferedReader estandar = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Imprimir la salida del proceso
            String line;
            System.out.println("[SALIDA ESTANDAR]");
            while ((line = estandar.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("[SALIDA ERROR]");
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = error.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Código de salida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al iniciar el proceso" + e.getMessage());
        }
    }
}