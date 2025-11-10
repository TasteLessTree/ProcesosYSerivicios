package org.ejercicios.tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/** @author AndrésPérezM
 * El comando cat o echo lo que hace es mostrar directamente lo que escribe el usuario en el comando o el contenido de un fichero.
 * En este ejemplo vamos a utilizarlo para escribir directamente el texto con el comando.
 * Utilizar el comando cat en linux o echo en windows para redirigir los flujos con el comando.
 * En windows: "cmd", "/c", "more & echo Texto de error de prueba 1>&2"
 * */

public class Ejercicio1 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "more & echo Texto de error de prueba 1>&2"); // Este texto producirá un error capturado por stderr
        // Para LINUX: processBuilder.command("bash", "-c", "cat; echo 'Texto de error de prueba'>&2");

        try {
            Process process = processBuilder.start();

            // Escribir datos en la entrada estándar del proceso (stdin)
            String stdin = "Este es mi texto de entrada estándar.\n";
            OutputStream outputStream = process.getOutputStream();
            outputStream.write(stdin.getBytes());
            outputStream.flush();
            outputStream.close();

            // Leer la salida del proceso (stdout)
            System.out.println("[STDOUT]");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String stdout;
            while ((stdout = reader.readLine()) != null) {
                System.out.println(stdout);
            }

            // Leer la salida de error del proceso (stderr)
            System.out.println("[STDERR]");
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String stderr;
            while ((stderr = error.readLine()) != null) {
                System.out.println(stderr);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nEl proceso a terminado con código de salida = " + exitCode);
        } catch (IOException e) {
            System.out.println("Error con el comando: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error al obtener el código de salida: " + e.getMessage());
        }
    }
}