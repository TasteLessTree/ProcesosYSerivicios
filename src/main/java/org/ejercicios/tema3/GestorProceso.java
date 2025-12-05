package org.ejercicios.tema3;

import java.net.ServerSocket;

/** @author AndrésPérezM
 * */

public class GestorProceso extends Thread {
    private static int numeroHilo = 0; // Solo se incializa una vez
    private ServerSocket servidor;
    private int espera;

    public GestorProceso(ServerSocket servidor) {
        super("Thread-" + numeroHilo);
        this.espera = esperarSegundos();
        this.servidor = servidor;
        numeroHilo++;
    }

    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();
        try {
            System.out.println("HILO (" + hilo + "): Atendiendo al cliente. Tiempo de proceso: " + espera + "s");
            Thread.sleep(espera * 1000L); // Por mil para pasar a milisegundos
            System.out.println("HILO (" + hilo + "): Finalizado. Respuesta enviada (" + espera + ").");
        } catch (InterruptedException e) {
            System.err.println("Error en el gestor del proceso: " + e.getMessage());
        }
    }

    // Espera entre 5 y 15 segundos, ambos incluidos
    private int esperarSegundos() {
        int rango = (15 - 5) + 1;
        return (int) (Math.random() * rango) + 5;
    }

    public String enviarMensaje() {
        return String.valueOf(espera);
    }
}