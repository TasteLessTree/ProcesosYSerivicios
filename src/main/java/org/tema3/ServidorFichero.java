package org.tema3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/** @author AndrésPérezM
 * */

public class ServidorFichero {
    private static final int PUERTO = 67;

    private ServerSocket servidor;
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;

    public ServidorFichero() {
    }

    private  void iniciar() {
        try {
            System.out.println("Iniciando servidor...");
            servidor = new ServerSocket(PUERTO);
            cliente = servidor.accept(); // Bloqueamos hasta que el cliente se conecte

            System.out.println("Servidor iniciado");

            // Abrimos el canal para leer la entradad del cliente
            abrirCanal();

            // Leemos el mensaje que manda el cliente (la ruta con el archivo)
            String ruta = leerMensaje();
            System.out.println("El servidor ha recivido el mensaje");

            // Enviar la respuesta
            enviarMensaje(leerFichero(ruta));

            // Cerramos el canal y la conexión
            cerrarCanal();
            cerrar();
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    private void cerrar() throws IOException {
        if (cliente != null) cliente.close();
        if (servidor != null) servidor.close();
    }

    private void abrirCanal() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(cliente.getOutputStream(), true);
    }

    private void cerrarCanal() throws IOException {
        if (entrada != null) entrada.close();
    }

    private String leerMensaje() throws IOException {
        return entrada.readLine();
    }

    private void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    private String leerFichero(String ruta) {
        StringBuilder texto = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean esUltimaLinea = false;

            while ((linea = reader.readLine()) != null) {
                if (esUltimaLinea) {
                    texto.append("\n");
                }
                texto.append(linea);
                esUltimaLinea = true;
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el fichero: " + e.getMessage());
        }

        return texto.toString();
    }

    public static void main(String[] args) {
        ServidorFichero servidor = new ServidorFichero();
        servidor.iniciar();
    }
}