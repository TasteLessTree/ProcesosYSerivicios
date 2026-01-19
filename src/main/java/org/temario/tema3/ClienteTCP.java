package org.temario.tema3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {
    private Socket socket;
    private BufferedReader entradaTexto;
    private PrintWriter salidaTexto;
    private String host;
    private int puerto;

    // Constructor
    public ClienteTCP(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void start() {
        try {
            // 1. (Cliente) Estableciendo conexión...
            System.out.println("(Cliente) Estableciendo conexión...");
            socket = new Socket(host, puerto);
            // 2. (Cliente) Conexión establecida.
            System.out.println("(Cliente) Conexión establecida.");
            // 3. (Cliente) Abriendo canales de texto...
            abrirCanalesDeTexto();
            System.out.println("(Cliente) Canales de texto abiertos.");
            // 4. Enviar mensaje al servidor
            System.out.println("(Cliente) Enviando mensaje...");
            enviarMensajeTexto("Mensaje enviado desde el cliente");
            System.out.println("(Cliente) Mensaje enviado.");
            // 5. Leer respuesta del servidor
            System.out.println("(Cliente) Leyendo mensaje...");
            String mensajeRecibido = leerMensajeTexto();
            // 6. Mostrar mensaje recibido
            System.out.println("(Cliente) Mensaje recibido:" + mensajeRecibido);
            System.out.println("(Cliente) Mensaje leido.");
            // 7. Cerrar canales
            System.out.println("(Cliente) Cerrando canales de texto.");
            cerrarCanalesDeTexto();
            System.out.println("(Cliente) Canales de texto cerrados.");
            // 8. Cerrar conexión
            System.out.println("(Cliente) Cerrando conexiones...");
            stop();
            System.out.println("(Cliente) Conexiones cerradas.");
        } catch (IOException e) {
            System.out.println("(Cliente) Error: " + e.getMessage());
        }
    }

    public void stop() throws IOException {
        if (socket != null) socket.close();
    }

    public void abrirCanalesDeTexto() throws IOException {
        entradaTexto = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salidaTexto = new PrintWriter(socket.getOutputStream(), true);
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
        // Asegúrate de iniciar primero el ServidorTCP
        ClienteTCP cliente = new ClienteTCP("localhost", 49171);
        cliente.start();
    }
}