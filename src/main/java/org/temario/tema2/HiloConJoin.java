package org.temario.tema2;

// Ejemplo5.java
public class HiloConJoin extends Thread {
    public HiloConJoin(String name) {
        super(name);
    }

    public void run() {
        System.out.println("Iniciado: " + getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Hilo de sleep interrumpido: " + e.getMessage());
        }

        System.out.println("Terminado: " + getName());
    }

    public static void main(String[] args) {
        System.out.println("Hilo principal iniciado");

        HiloConJoin hilo = new HiloConJoin("Hilo1");
        HiloConJoin hilo2 = new HiloConJoin("Hilo2");

        hilo.start();
        hilo2.start();

        try {
            System.out.println("El hilo principal espera a que hilo1 termine");
            hilo.join(); // Main se bloquea hasta que 'hilo' termine

            System.out.println("El hilo principal espera a que hilo2 termine");
            hilo2.join(); // Main se bloquea hasta que 'hilo2' termine
        } catch (InterruptedException e) {
            System.out.println("Error con el join de los hilos: " + e.getMessage());
        }

        System.out.println("Hilo principal finalizado (hilo e hilo2 también)");
    }
}