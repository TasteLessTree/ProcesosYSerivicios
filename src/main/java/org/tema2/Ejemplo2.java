package org.tema2;

public class Ejemplo2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Ejemplo2 ha entrado en el método RUN");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Ejemplo2 (implementado de Runnable): " + i);

            try {
                Thread.sleep(500); // Dormir durante 0.5 segundos
            } catch (InterruptedException e) {
                System.out.println("Hilo de sleep ha sido interrumpido: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Runnable ejemplo = new Ejemplo2();
        Thread t1 = new Thread(ejemplo); // Se pasa el Runnable
        t1.start();
        System.out.println("El hilo principal se sigue ejecutando");
    }
}