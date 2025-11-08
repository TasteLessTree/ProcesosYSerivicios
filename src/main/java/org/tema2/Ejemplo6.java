package org.tema2;

public class Ejemplo6 {
    public static void main(String[] args) {
        Piscina piscina = new Piscina();
        int numVecionos = 7;

        System.out.println("Empieza el día de piscina: " + numVecionos + " quieren ir al agua.\n");

        for (int i = 1; i <= numVecionos; i++) {
            Vecino vecino = new Vecino("Vecino-" + i, piscina);
            vecino.start();

            // Pequeña pausa para mejorar la simulació
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo sleep main: " + e.getMessage());
            }
        }
    }
}