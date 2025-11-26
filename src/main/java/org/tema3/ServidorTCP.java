package org.tema3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTCP {
    private static String host = "localhost";
    private static int puerto = 67;

    public ServidorTCP(String host, int puerto) {
        ServidorTCP.host = host;
        ServidorTCP.puerto = puerto;
    }

    public ServidorTCP() {
        ServidorTCP.host = "localhost";
        ServidorTCP.puerto = 67;
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            System.out.println("Esperando conexiones...");
            InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), puerto);
            serverSocket.bind(address);

            Socket socket = serverSocket.accept();

            enviarMensajeTexto(socket);
            leerMensajeTexto(socket);
            cerrarCanalDeTexto(socket);
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Cerrar el canal
    private static void cerrarCanalDeTexto(Socket socket) throws IOException {
        socket.close();
    }

    // Enviar mensaje de texto
    private static void enviarMensajeTexto(Socket socket) throws IOException {
        try (Scanner scanner = new Scanner(System.in); DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            System.out.print(">> ");
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
            System.out.println("\u001B[33m Cliente" + mensaje + "\u001B[37m");
        } catch (IOException ioe) {
            System.out.println("Error al leer mensaje de texto: " + ioe.getMessage());
        }
    }
}