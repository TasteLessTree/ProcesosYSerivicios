package org.ejercicios.tema3;

/** @author AndrésPérezM
 * */

public class GestorProceso extends Thread {
    private static int numeroHilo = 0; // Solo se incializa una vez
    private int espera;

    public GestorProceso() {
        super("Thread-" + numeroHilo);
        this.espera = esperarSegundos();
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

    public int getEspera() {
        return espera;
    }
}