package org.tema3;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {
    public static void main(String[] args) {
        // Datos de la conexón
        String host = "localhost";
        int port = 42069;

        System.out.println("Esperando conexiones...");

        // Cerrar el socket al final
        try (ServerSocket serverSocket = new ServerSocket()) {
            InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
            serverSocket.bind(address);

            // "accept()" es bloqueante. El programa espera hasta que llegue un cliente
            Socket socket = serverSocket.accept();
            System.out.println("Conexión establecida con éxito");

            // Obtener flujos de la comunicación
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // Leer del cliente
            // "readInt()" es bloqueante. Espera hasta que lleguen 4 bytes (un entero)
            int mensaje = dataInputStream.readInt();
            System.out.println("Mensaje del cliente: " + mensaje);

            // Escribir la cliente
            dataOutputStream.writeInt(200);
            // "flush()" fuerza el envío de datos que puedan quedar en el buffer
            dataOutputStream.flush();

            System.out.println("Cerrando conexión...");

            // Cerramos la conexión
            socket.close();
            System.out.println("Coneción cerrada con éxito");
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}