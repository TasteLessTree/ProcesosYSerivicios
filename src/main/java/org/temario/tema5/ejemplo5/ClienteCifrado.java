package org.temario.tema5.ejemplo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteCifrado {
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private String host;
    private int puerto;

    public ClienteCifrado(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void iniciar() {
        try {
            // Establecer conexión
            System.out.println("[CLIENTE] Estableciendo conexión...");
            cliente = new Socket(host, puerto);

            System.out.println("[CLIENTE] Conexión establecida");

            // Abrir canales de texto
            abrirCanales();

            // Enviar mensaje al servidor
            System.out.println("[CLIENTE] Enviando mensaje");
            enviarMensaje(EncriptacionManager.encriptar("Adios Marte desde el cliente"));
            System.out.println("[CLIENTE] Mensaje enviado");

            // Leer respuesta del servidor
            String mensaje = leerMensaje();
            System.out.println("[CLIENTE] mensaje recibido: " + mensaje);
            System.out.println("[CLIENTE] mensaje descencriptado: " + EncriptacionManager.desencriptar(mensaje));

            // Cerrar canales y conexión
            cerrarCanales();
            parar();

            System.out.println("[CLIENTE] Cliente cerrado");
        } catch (IOException e) {
            System.err.println("[CLIENTE] Error en el cliente: " + e.getMessage());
        }
    }

    public void parar() throws IOException {
        if (cliente != null) cliente.close();
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
        ClienteCifrado clienteCifrado = new ClienteCifrado("localhost", 49171);
        clienteCifrado.iniciar();
    }
}