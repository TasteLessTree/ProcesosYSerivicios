package org.temario.tema5.ejemplo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCifrado {
    private ServerSocket servidor;
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private int puerto;

    public ServidorCifrado(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try {
            // 1. Esperar conexiones
            System.out.println("[SERVIDOR] Iniciando...");
            servidor = new ServerSocket(puerto);

            // Aceptamos la conexión
            cliente = servidor.accept();
            System.out.println("[SERVIDOR] Conexión establecida");

            // Abrir canales
            abrirCanales();

            // Leer mensajes del cliente
            String mensaje = leerMensaje();
            System.out.println("[SERVIDOR] mensaje recibido: " + mensaje);
            System.out.println("[SERVIDOR] mensaje descencriptado: " + EncriptacionManager.desencriptar(mensaje));

            // Enviar mensaje al cliente
            System.out.println("[SERVIDOR] Enviando mensaje...");
            enviarMensaje(EncriptacionManager.encriptar("Hola mundo desde un servidor encriptado"));
            System.out.println("[SERVIDOR] Mensaje enviado");

            // Cerrar canales y conexión
            cerrarCanales();
            parar();

            System.out.println("[SERVIDOR] Servidor cerrado");
        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error con el servidor: " + e.getMessage());
        }
    }

    public void parar() throws IOException {
        if (cliente != null) cliente.close();
        if (servidor != null) servidor.close();
    }

    public void abrirCanales() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(cliente.getOutputStream(), true);
    }

    public void cerrarCanales() throws IOException {
        if (entrada  != null) entrada.close();
        if (salida != null) salida.close();
    }

    public String leerMensaje() throws IOException {
        return entrada.readLine();
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    public static void main(String[] args) {
        ServidorCifrado servidor = new ServidorCifrado(49171);
        servidor.iniciar();
    }
}