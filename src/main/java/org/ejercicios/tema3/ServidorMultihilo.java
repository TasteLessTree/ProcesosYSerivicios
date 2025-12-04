package org.ejercicios.tema3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** @author AndrésPérezM
 * */

public class ServidorMultihilo {
    private ServerSocket servidor;
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private int puerto;

    public ServidorMultihilo(int puerto) {
        this.puerto = puerto;
    }

    // Iniciar el servidor
    public void iniciar() {
        try {
            System.out.println("SERVIDOR: Escuchando en el puerto: " + puerto);

            // Esperamos la conexión y la aceptamos
            servidor = new ServerSocket(puerto);
            System.out.println("SERVIDOR: Esperando cliente...");
            cliente = servidor.accept();

            // Abrir canales de texto
            abrirCanales();

            // Conexión establecida
            InetAddress direccionCliente = cliente.getLocalAddress();
            String direccion = direccionCliente.getHostAddress();
            System.out.println("SERVIDOR: Cliente conectado desde /" + direccion);

            // Enviar respuesta al cliente
            GestorProceso gestorProceso = new GestorProceso();
            gestorProceso.start();
            String mensaje = String.valueOf(gestorProceso.getEspera());
            enviarMensaje(mensaje);

            // Cerrar canales de texto
            cerrarCanales();

            // Cerrar la conexión
            parar();
        } catch (IOException e) {
            System.err.println("SERVIDOR: error en el servidor: " + e.getMessage());
        }
    }

    private void abrirCanales() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(cliente.getOutputStream(), true);
    }

    private void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    private void cerrarCanales() throws IOException {
        if (entrada != null) entrada.close();
        if (salida != null) salida.close();
    }

    private void parar() throws IOException {
        if (servidor != null) servidor.close();
        if (cliente != null) cliente.close();
    }

    public static void main(String[] args) {
        ServidorMultihilo servidorMultihilo = new ServidorMultihilo(49171);
        servidorMultihilo.iniciar();
    }
}