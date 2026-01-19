package org.ejercicios.tema5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegistrarCredenciales {
    // Fichero
    protected final static String PATH = "src/main/java/org/ejercicios/tema5/datos.txt";

    // Registrar un usuario (en criptado) en un fichero
    public void pedirDatos() {
        Scanner sc = new Scanner(System.in);

        // Pedir los datos al usuario
        System.out.print("Introduce tu correo electrónico: ");
        String correo = sc.nextLine();

        System.out.print("Introduce tu contraseña: ");
        String contrasenya = sc.nextLine();

        // Encriptar datos
        HASHManager hashManager = new HASHManager();
        String correoSeguro = hashManager.encriptar(correo);
        String contrasenyaSegura = hashManager.encriptar(contrasenya);

        if (correoSeguro == null || contrasenyaSegura == null) {
            System.out.println("No se han encriptado los datos de forma correcta. Saliendo...");
            System.exit(1);
        }

        // Guardar datos en el fichero
        escribirDatos(correoSeguro, contrasenyaSegura);
    }

    // Escribir datos en el fichero
    private void escribirDatos(String correo, String contrasenya) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(correo + " | " +  contrasenya);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RegistrarCredenciales().pedirDatos();
    }
}