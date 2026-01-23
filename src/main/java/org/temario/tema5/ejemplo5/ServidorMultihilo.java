package org.temario.tema5.ejemplo5;

import org.ejercicios.tema3.GestorProceso;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** @author AndrésPérezM
 * */

public class ServidorMultihilo {
    private ServerSocket servidor;
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

            while (true) {
                System.out.println("SERVIDOR: Esperando cliente...");
                Socket cliente = servidor.accept();

                // Conexión establecida
                InetAddress direccionCliente = cliente.getLocalAddress();
                String direccion = direccionCliente.getHostAddress();
                System.out.println("SERVIDOR: Cliente conectado desde /" + direccion);

                // Enviar respuesta al cliente
                org.ejercicios.tema3.GestorProceso gestorProceso = new GestorProceso(cliente);
                gestorProceso.start();
            }
        } catch (IOException e) {
            System.err.println("SERVIDOR: error en el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ServidorMultihilo servidorMultihilo = new ServidorMultihilo(49171);
        servidorMultihilo.iniciar();
    }
}