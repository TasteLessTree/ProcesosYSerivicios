package org.ejercicios.tema3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/** @author AndrésPérezM
 * */

public class GestorProceso extends Thread {
    private static int numeroHilo = 0; // Solo se incializa una vez

    private Socket cliente;
    private int espera;

    public GestorProceso(Socket cliente) {
        super("Thread-" + numeroHilo);
        this.espera = esperarSegundos();
        this.cliente = cliente;
        numeroHilo++;
    }

    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();
        try {
            System.out.println("HILO (" + hilo + "): Atendiendo al cliente. Tiempo de proceso: " + espera + "s");
            Thread.sleep(espera * 1000L); // Por mil para pasar a milisegundos

            // Enviar la salida al cliente
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            salida.println(String.valueOf(espera));
            System.out.println("HILO (" + hilo + "): Finalizado. Respuesta enviada (" + espera + ").");

            // Cerrar el cliente y la salida
            salida.close();
            cliente.close();
        } catch (InterruptedException e) {
            System.err.println("Error en el gestor del proceso: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al enviar la salida al cliente: " + e.getMessage());
        }
    }

    // Espera entre 5 y 15 segundos, ambos incluidos
    private int esperarSegundos() {
        int rango = (15 - 5) + 1;
        return (int) (Math.random() * rango) + 5;
    }
}