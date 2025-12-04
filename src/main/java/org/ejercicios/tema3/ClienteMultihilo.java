package org.ejercicios.tema3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** @author AndrésPérezM
 * */

public class ClienteMultihilo {
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private String host;
    private int puerto;

    public ClienteMultihilo(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void iniciar() {
        try {
            System.out.println("CLIENTE: Conectando al servidor...");

            // Nos conectamos
            cliente = new Socket(host, puerto);
            System.out.println("CLIENTE: Conectado. Esperando resultado del proceso...");

            // Abrimos los canales de texto
            abrirCanales();

            // Leemos la salida
            String mensaje = leerMensaje();
            System.out.println("CLIENTE: El servidor ha terminado la tarea en " + mensaje + " segundos.");

            // Cerramos canales de texto y la conexión
            cerrarCanales();
            parar();
        } catch (IOException e) {
            System.err.println("CLIENTE: Error en el cliente: " + e.getMessage());
        }
    }

    private void abrirCanales() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(cliente.getOutputStream(), true);
    }

    public String leerMensaje() throws IOException {
        return entrada.readLine();
    }

    private void cerrarCanales() throws IOException {
        if (entrada != null) entrada.close();
        if (salida != null) salida.close();
    }

    private void parar() throws IOException {
        if (cliente != null) cliente.close();
    }

    public static void main(String[] args) {
        ClienteMultihilo clienteMultihilo = new ClienteMultihilo("localhost", 49171); // localhost -> 127.0.0.1
        clienteMultihilo.iniciar();
    }
}