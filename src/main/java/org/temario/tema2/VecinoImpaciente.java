package org.temario.tema2;

import java.util.concurrent.Semaphore;

// Ejemplo 8
public class VecinoImpaciente extends Thread {
    private final PiscinaImpaciente piscina;

    public VecinoImpaciente(String nombre, PiscinaImpaciente piscina) {
        super(nombre);
        this.piscina = piscina;
    }
        @Override
        public void run() {
        String nombre = getName();
        Semaphore torniquete = piscina.getTorniquete();
        System.out.println(nombre + ": Llega a la puerta de la piscina a ver si hay sitio...");
        // --- ¡AQUÍ ESTÁ LA MAGIA! ---
        // Intenta coger un permiso. Si no hay, no espera.
        if (torniquete.tryAcquire()) {
            // --- CAMINO 1: SÍ PUDO ENTRAR ---
            System.out.println(" -> " + nombre + ": ¡Genial, hay sitio! A nadar. (Quedan " + torniquete.availablePermits() + " sitios)");
            try {
                // Simula el tiempo nadando
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Libera el sitio al salir
                System.out.println(" <- " + nombre + ": Terminé. Devolviendo el pase.");
                torniquete.release();
            }
        } else {
            // --- CAMINO 2: NO PUDO ENTRAR ---
            // El hilo no se bloqueó, simplemente tryAcquire() devolvió 'false'
            System.out.println(" -- " + nombre + ": ¡Vaya, está llenísimo! Me doy la vuelta y me voy al BAR.");
        }
        System.out.println(nombre + ": Ha terminado su gestión de hoy.");
    }
}