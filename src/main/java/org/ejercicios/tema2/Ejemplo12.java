package org.ejercicios.tema2;

import java.util.concurrent.Semaphore;

/** @author AndrésPérezM
 * Ejemplo 12
 * */

public class Ejemplo12 {
    // Main
    public static void main(String[] args) {
        Horno horno = new Horno();

        Panadero pA = new Panadero("Panadero-A", horno);
        Panadero pB = new Panadero("Panadero-B", horno);

        pA.start();
        pB.start();
    }

    // Horno
    static class Horno {
        private final int CAPACIDAD = 1;

        private final Semaphore horno =  new Semaphore(CAPACIDAD, true);

        private void usarHorno() {
            String panadero = Thread.currentThread().getName();

            try {
                System.out.println("🥣 " + panadero + " 🧑‍🍳 está PREPARANDO la masa. (3 seg)");
                Thread.sleep(3000);

                horno.acquire();
                System.out.println("⌛ " + panadero + " 🧑‍🍳 está ESPERANDO el horno.");

                System.out.println("🔥 " + panadero + " 🧑‍🍳 está HORNEANDO... (7 seg)");
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                System.out.println("Error en el horno al horno: " + e.getMessage());
            } finally {
                horno.release();
            }
        }
    }

    // Panaderos
    static class Panadero extends Thread {
        private final Horno horno;

        public Panadero(String nombre, Horno horno) {
            super(nombre);
            this.horno = horno;
        }

        @Override
        public void run() {
            int pastelesTotales = 4;
            int numPasteles = 0;
            String panadero = Thread.currentThread().getName();

            for (int i = 0; i < pastelesTotales; i++) {
                horno.usarHorno();
                numPasteles++;
                System.out.println("🍞 " + panadero + " 🧑‍🍳 ha TERMINADO de hornear el pastel número " + numPasteles);
            }
        }
    }
}