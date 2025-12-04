package org.tema3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/** @author AndrésPérezM
 * */

public class ClienteFichero {
    private static final int PUERTO = 67;
    private static final String HOST = "localhost";

    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;

    public ClienteFichero() {
    }

    private void iniciar() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Iniciando cliente...");
            cliente = new Socket(HOST, PUERTO);

            System.out.println("Cliente inciado");

            // Abrimos el canal
            abrirCanal();

            // Enviar mensaje al servidor. Tiene que ser la ruta de un archivo, si no exite, volver a preguntar
            System.out.print("Introduce el nombre del fichero: ");
            String fichero = sc.nextLine();

            File file = new File(fichero); // Es necesario mandar la ruta absoluta, no estoy muy seguro de porqué

            while (!file.exists()) {
                abrirCanal();
                System.out.println("\nNo existe el fichero");
                System.out.print("Introduce el nombre del fichero: ");
                fichero = sc.nextLine();
                file = new File(fichero);
            }

            enviarMensaje(fichero);

            // Leer la respuesta del servidor
            System.out.println("\n\"\"\"\n" + leerMensaje() + "\n\"\"\"\n");

            // Cerrar canales y la conexión
            cerrarCanal();
            cerrar();
        } catch (IOException e) {
            System.out.println("Error al iniciar el cliente: " + e.getMessage());
        }

        sc.close();
    }

    private void cerrar() throws IOException {
        if (cliente != null) cliente.close();
    }

    private void abrirCanal() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(cliente.getOutputStream(), true);
    }

    private void cerrarCanal() throws IOException {
        if (entrada != null) entrada.close();
    }

    private void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    private String leerMensaje() throws IOException {
        return entrada.readLine();
    }

    public static void main(String[] args) {
        ClienteFichero cliente = new ClienteFichero();
        cliente.iniciar();
    }
}