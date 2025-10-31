package org.ejercicios;

import java.io.IOException;

/** @author AndrésPérezM
 * El comando cat o echo lo que hace es mostrar directamente lo que escribe el usuario en el comando o el contenido de un fichero.
 * En este ejemplo vamos a utilizarlo para escribir directamente el texto con el comando.
 * Utilizar el comando cat en linux o echo en windows para redirigir los flujos con el comando.
 * En windows: "cmd", "/c", "type con & echo 'Texto de error de prueba'1>&2"
 * */
public class Ejercicio1 {
    // Linux: "bash", "-c", "cat; echo 'Texto de error de prueba'>&2"

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "type con & echo 'Texto de error de prueba'1>&2'");

        try {
            Process process = processBuilder.start();
        } catch (IOException e) {
            System.out.println("Error con el comando: " + e.getMessage());
        }
    }
}