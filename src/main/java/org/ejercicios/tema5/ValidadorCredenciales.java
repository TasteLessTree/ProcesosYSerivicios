package org.ejercicios.tema5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ValidadorCredenciales {
    // Fichero
    protected final static String PATH = "src/main/java/org/ejercicios/tema5/datos.txt";

    // Verificar hashes
    public static boolean esElMismoHash(String contenido, String hash) {
        // Obtener el hash
        HASHManager hashManager = new HASHManager();
        String contenidoSeguro = hashManager.encriptar(contenido);
        return contenidoSeguro.equals(hash);
    }

    // Leer obtener datos 0 -> correo | 1 -> contraseña
    public static String obtenerDatosFichero(String path, int dato) {
         try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            StringBuilder contenido = new StringBuilder();

            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }

            return  contenido.toString().split("\\|")[dato].trim(); // Solo funciona con un par correo-contraseña
        } catch (IOException e) {
            System.err.println("Error al leer el correo: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir los datos al usuario
        System.out.print("Introduce tu correo electrónico: ");
        String correo = sc.nextLine();

        System.out.print("Introduce tu contraseña: ");
        String contrasenya = sc.nextLine();

        System.out.println("Es la contraseña la misma: " + esElMismoHash(contrasenya, obtenerDatosFichero(PATH, 1)));

        System.out.println("Es el mismo correo: " + esElMismoHash(correo, obtenerDatosFichero(PATH, 0)));
    }
}