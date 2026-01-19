package org.temario.tema2;

import java.util.concurrent.Semaphore;

// Ejemplo 7
public class PoolConexionesDB {
    private static final int CONEXION_MAX = 3;

    private final Semaphore conexionSemaphore = new Semaphore(CONEXION_MAX, true);

    public void conectar() {
        String usuario = Thread.currentThread().getName();

        try {
            System.out.println("\033[1;33m" + usuario + " se quiere conectar \033[0m\n");

            conexionSemaphore.acquire();
            System.out.println("\033[1;32m" + usuario + " se ha conectado. (" + conexionSemaphore.availablePermits() + " sitios libres)\033[0m\n");

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error con los semáforos: " + e.getMessage());
        } finally {
            System.out.println("\033[1;31m" + usuario + " se ha desconectado. (" + (conexionSemaphore.availablePermits() + 1) + " sitios libres)\033[0m\n");
            conexionSemaphore.release();
        }
    }
}