package org.temario.tema3;

import java.io.*;
import java.net.Socket;

public class SocketTCPClient {
    public static void main(String[] args) {
        // Datos de la conexón
        String host = "localhost";
        int port = 42069;

        System.out.println("Esperando conexiones...");

        // Cerrar el socket al final
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conexión establecida.");

            // Obtener flujos
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            // Enviar datos al servidor
            dataOutputStream.writeInt(100);
            // Aseguramos que sale del buffer
            dataOutputStream.flush();

            // El cliente espera a que el servidor responda
            int respuesta = dataInputStream.readInt();
            System.out.println("Mensaje del servidor: " + respuesta);

            System.out.println("Cerrando conexiones...");
            // Cerramos la conexión
            socket.close();
            System.out.println("Conexión cerrada con éxito");
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}