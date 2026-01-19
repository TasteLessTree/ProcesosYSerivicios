package org.temario.tema1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Ejercicio5 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "sort");

        try {
            Process process = processBuilder.start();

            // Escribir al output
            OutputStream outputStream = process.getOutputStream();
            outputStream.write("manzana\nbanana\npera\nmelocotón\nfresa\n".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            // Leer
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error con el comando: " + e.getMessage());
        }
    }
}