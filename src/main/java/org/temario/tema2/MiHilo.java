package org.temario.tema2;

// Ejemplo1
public class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("MiHilo ha entrado en el método RUN");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo (extendido de THREAT): " + i);

            try {
                Thread.sleep(500); // Dormir durante 0.5 segundos
            } catch (InterruptedException e) {
                System.out.println("Hilo de sleep ha sido interrumpido: " + e.getMessage());
            }
        }
    }
}