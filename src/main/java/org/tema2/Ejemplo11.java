package org.tema2;

import java.util.concurrent.Semaphore;

public class Ejemplo11 {
    private static final int[] esperas = {200, 300, 100, 400, 0};

    public static void main(String[] args) {
        Puente puente = new Puente();

        int numVehiculos = esperas.length; // Tantos vehículos cómo esperas (5)

        for (int i = 0; i < numVehiculos; i++) {
            Vehiculo vehiculo = new Vehiculo("Vehículo-" + (i + 1), puente);
            vehiculo.start();

            try {
                Thread.sleep(esperas[i]);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo sleep main: " + e.getMessage());
            }
        }
    }

    static class Puente {
        private static final int AFORO = 1;

        // Primero en entrar -> primero en salir
        private final Semaphore semaphore = new Semaphore(AFORO, true);

        public void usarPuente() {
            String vehiculo = Thread.currentThread().getName();

            try {
                System.out.println("\033[1;33m" + vehiculo + " -> está esperando para cruzar el puente \033[0m\n");
                semaphore.acquire();
                System.out.println("\033[1;32m" + vehiculo + " -> está cruzando el puente \033[0m\n");

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Error en el puente: " + e.getMessage());
            } finally {
                semaphore.release();
            }
        }
    }

    static class Vehiculo extends Thread {
        private final Puente puente;

        public Vehiculo(String nombre, Puente puente) {
            super(nombre);
            this.puente = puente;
        }

        @Override
        public void run() {
            puente.usarPuente();
        }
    }
}