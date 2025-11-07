package org.tema2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejemplo3 extends Thread {
    String command;

    public Ejemplo3(String task) {
        this.command = task;
    }

    @Override
    public void run() {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(command.split(","));

        try {
            System.out.println("Iniciando proceso...");
            Process process = pb.start();

            // Crea el BufferedReader y guarda el output del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Lee el output del proceso
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Proceso: " + command + "finalizado con: " + exitCode);
        } catch (IOException e) {
            System.out.println("Error al iniciar proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error al obtener el código de salida: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Ejemplo3 hilo1 = new Ejemplo3("ping -n 3 www.google.com");
        Ejemplo3 hilo2 = new Ejemplo3("ping -n 3 www.github.com");

        hilo1.start();
        hilo2.start();
    }
}