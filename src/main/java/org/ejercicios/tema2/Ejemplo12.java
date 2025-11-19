package org.ejercicios.tema2;

import java.util.concurrent.Semaphore;

/** @author AndrésPérezM
 * Ejemplo 12
 * */

public class Ejemplo12 {
    // Main
    public static void main(String[] args) {
        Horno horno = new Horno();

        String[] nombres = {"A", "B"};

        for (String nombre : nombres) {
            Panadero panadero = new Panadero("Panadero-" + nombre, horno);
            panadero.start();
        }
    }

    // Horno
    static class Horno {
        private final int CAPACIDAD = 1;

        private final Semaphore acceso =  new Semaphore(CAPACIDAD);

        // Número de pastales que han horneado
        private int numPasteles = 0;

        private void usarHorno(int pastelesTotales) {
            String panadero = Thread.currentThread().getName();

            for (int i = 0; i < pastelesTotales; i++) {
                try {
                    System.out.println("🥣 " + panadero + " 🧑‍🍳 está PREPARANDO la masa. (3 seg)");
                    Thread.sleep(3000);

                    System.out.println("⌛ " + panadero + " 🧑‍🍳 está ESPERANDO el horno.");
                    acceso.acquire();

                    System.out.println("🔥 " + panadero + " 🧑‍🍳 está HORNEANDO... (7 seg)");
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    System.out.println("Error en el acceso al horno: " + e.getMessage());
                } finally {
                    numPasteles++;
                    acceso.release();
                    System.out.println("🍞 " + panadero + " 🧑‍🍳 ha TERMINADO de hornear el pastél número " + numPasteles);
                }
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
            horno.usarHorno(pastelesTotales);
        }
    }
}