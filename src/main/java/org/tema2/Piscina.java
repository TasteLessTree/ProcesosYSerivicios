package org.tema2;

import java.util.concurrent.Semaphore;

// Ejemplo 6
public class Piscina {
    private static final int AFORO_MAX = 3;

    // Primero en entrar -> Primero en salir
    private final Semaphore torniquete = new Semaphore(AFORO_MAX, true);

    public void usarPiscina() {
        String nombreVecino = Thread.currentThread().getName();

        try {
            System.out.println("\033[1;32m" + nombreVecino + ": Llega a la puerta de la piscina\033[0m\n");

            torniquete.acquire();

            System.out.println(nombreVecino + ", ¡Al agua! (Quedan: " + torniquete.availablePermits() + " sitios libres)\033[0m\n");
            Thread.sleep(3000);

            System.out.println("\033[1;33m" + nombreVecino + " ya ha tenido bastastante.\033[0m\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error con los semáforos: " + e.getMessage());
        } finally {
            System.out.println("\033[1;31m" + nombreVecino + " ha abandonado la piscina. (" + (torniquete.availablePermits() + 1) + " sitios libres)\033[0m\n");
            torniquete.release();
        }
    }
}