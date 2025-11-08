package org.tema2;

import java.util.concurrent.Semaphore;

public class Piscina {
    private static final int AFORO_MAX = 3;

    // Primero en entrar -> Primero en salir
    private final Semaphore torniquete = new Semaphore(AFORO_MAX, true);

    public void usarPiscina() {
        String nombreVecino = Thread.currentThread().getName();

        try {
            System.out.println(nombreVecino + ": Llega a la puerta de la piscina\n");

            torniquete.acquire();

            System.out.println(nombreVecino + ", ¡Al agua! (Quedan: " + torniquete.availablePermits() + " sitios libres)\n");
            Thread.sleep(3000);

            System.out.println(nombreVecino + " ya ha tenido bastastante.\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error con los semáforos: " + e.getMessage());
        } finally {
            System.out.println(nombreVecino + " ha abandonado la piscina. (" + (torniquete.availablePermits() + 1) + " sitios libres)\n");
            torniquete.release();
        }
    }
}