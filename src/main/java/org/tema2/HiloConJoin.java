package org.tema2;

public class HiloConJoin extends Thread {
    public HiloConJoin(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Hilo: " + this.getName() + " - i = " + i);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hilo principal iniciado");

        HiloConJoin hilo = new HiloConJoin("Hilo1");
        HiloConJoin hilo2 = new HiloConJoin("Hilo2");

        hilo.start();
        hilo2.start();

        try {
            System.out.println("El hilo principal espera a que hilo1 termine");
            hilo.join();

            System.out.println("El hilo principal espera a que hilo2 termine");
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("Error con el join de los hilos: " + e.getMessage());
        }

        System.out.println("Hilo principal finalizado");
    }
}