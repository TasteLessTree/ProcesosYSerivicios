package org.tema3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    private static String host = "localhost";
    private static int puerto = 67;

    public ClienteTCP(String host, int puerto) {
        ClienteTCP.host = host;
        ClienteTCP.puerto = puerto;
    }

    public ClienteTCP() {
        ClienteTCP.host = "localhost";
        ClienteTCP.puerto = 67;
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try (Socket socket = abrirCanalDeTexto()) {
            System.out.println("Conexión establecida");
            enviarMensajeTexto(socket);
            leerMensajeTexto(socket);
            cerrarCanalDeTexto(socket);
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Abrir
    private static Socket abrirCanalDeTexto() throws IOException {
        return new Socket(host, puerto);
    }

    // Cerrar el canal
    private static void cerrarCanalDeTexto(Socket socket) throws IOException {
        socket.close();
    }

    // Enviar mensaje de texto
    private static void enviarMensajeTexto(Socket socket) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(">> ");
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            dataOut.writeUTF(scanner.nextLine());
            dataOut.flush();
        } catch (IOException ioe) {
            System.out.println("Error al enviar el mensaje de texto: " + ioe.getMessage());
        }
    }

    // Leer el mensaje
    private static void leerMensajeTexto(Socket socket) throws IOException {
        try (DataInputStream dataIn = new DataInputStream(socket.getInputStream())) {
            String mensaje = dataIn.readUTF();
            System.out.println("\u001B[32m Servidor: " + mensaje + "\u001B[37m");
        } catch (IOException ioe) {
            System.out.println("Error al leer mensaje de texto: " + ioe.getMessage());
        }
    }
}