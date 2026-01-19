package org.temario.tema3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    private ServerSocket serverSocket;
    private Socket socketCliente;
    private BufferedReader entradaTexto;
    private PrintWriter salidaTexto;
    private int puerto;

    // Constructor
    public ServidorTCP(int puerto) {
        this.puerto = puerto;
    }

    public void start() {
        try {
            // 1. (Servidor) Esperando conexiones...
            System.out.println("(Servidor) Esperando conexiones...");
            serverSocket = new ServerSocket(puerto);
            // Aceptamos la conexión (bloqueante)
            socketCliente = serverSocket.accept();
            // 2. (Servidor) Conexión establecida.
            System.out.println("(Servidor) Conexión establecida.");
            // 3. (Servidor) Abriendo canales de texto...
            abrirCanalesDeTexto();
            System.out.println("(Servidor) Canales de texto abiertos.");
            // 4. Leer mensaje del cliente
            System.out.println("(Servidor) Leyendo mensaje...");
            String mensajeRecibido = leerMensajeTexto();
            // 5. Mostrar mensaje recibido
            System.out.println("(Servidor) Mensaje recibido:" + mensajeRecibido);
            System.out.println("(Servidor) Mensaje leido.");
            // 6. Enviar respuesta
            System.out.println("(Servidor) Enviando mensaje...");
            enviarMensajeTexto("Mensaje enviado desde el servidor");
            System.out.println("(Servidor) Mensaje enviado.");
            // 7. Cerrar canales
            System.out.println("(Servidor) Cerrando canales de texto.");
            cerrarCanalesDeTexto();
            System.out.println("(Servidor) Canales de texto cerrados.");
            // 8. Cerrar conexión
            System.out.println("(Servidor) Cerrando conexiones...");
            stop();
            System.out.println("(Servidor) Conexiones cerradas.");
        } catch (IOException e) {
            System.out.println("(Servidor) Error: " + e.getMessage());
        }
    }

    public void stop() throws IOException {
        if (socketCliente != null) socketCliente.close();
        if (serverSocket != null) serverSocket.close();
    }

    public void abrirCanalesDeTexto() throws IOException {
        entradaTexto = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        salidaTexto = new PrintWriter(socketCliente.getOutputStream(), true);
    }

    public void cerrarCanalesDeTexto() throws IOException {
        if (entradaTexto != null) entradaTexto.close();
        if (salidaTexto != null) salidaTexto.close();
    }

    public String leerMensajeTexto() throws IOException {
        return entradaTexto.readLine();
    }

    public void enviarMensajeTexto(String mensaje) {
        salidaTexto.println(mensaje);
    }

    public static void main(String[] args) {
        ServidorTCP servidor = new ServidorTCP(49171);
        servidor.start();
    }
}