package org.tema2;

import java.util.concurrent.Semaphore;

public class Ejemplo8 {
    public static void main(String[] args) {
        PiscinaImpaciente piscina = new PiscinaImpaciente();
        int numVecionos = 7;

        System.out.println("Empieza el día de piscina: " + numVecionos + " quieren ir al agua.\n");

        for (int i = 1; i <= numVecionos; i++) {
            VecinoImpaciente vecino = new VecinoImpaciente("Vecino-" + i, piscina);
            vecino.start();

            // Pequeña pausa para mejorar la simulación
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo sleep main: " + e.getMessage());
            }
        }
    }
}