package org.tema2;

import java.util.concurrent.Semaphore;

public class Ejemplo13 {
    private static Semaphore salaEspera = new Semaphore(4, true);
    private static Semaphore cliente = new Semaphore(0);
    private static Semaphore barbero = new Semaphore(0);

    public static void main(String[] args) {
        System.out.println("Barbería abierta");
        Barbero barbero = new Barbero();

        barbero.start();

        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente(i);
            cliente.start();

            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static class Cliente extends Thread {
        private int id;

        public Cliente(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                if (salaEspera.tryAcquire()) {
                    System.out.println("El cliente " + id + " ha entrado en la sala de espera. Sitios libres: " + salaEspera.availablePermits());
                    cliente.release();
                    barbero.acquire();
                    salaEspera.release();

                    System.out.println("El cliente " + id + " se ha sentado en la silla del barbero y le cortan el pelo.");
                } else {
                    System.out.println("Cliente " + id + " se va enfadado. La sala está llena");
                }
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static class Barbero extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    if (!cliente.tryAcquire()) {
                        System.out.println("El barbero está durmiendo");
                        cliente.acquire();
                    }

                    System.out.println("El barbero está despierto");
                    barbero.release();

                    System.out.println("El barbero está cortando el pelo (5 seg.)");
                    Thread.sleep(5000);

                    System.out.println("El barbero ha terminado el corte");
                } catch (InterruptedException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}