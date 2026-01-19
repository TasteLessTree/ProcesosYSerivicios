package org.temario.tema2;

import java.util.concurrent.Semaphore;

// Ejemplo 8
public class PiscinaImpaciente {
    private static final int AFORO_MAX = 3;

    // Primero en entrar -> Primero en salir
    private final Semaphore torniquete = new Semaphore(AFORO_MAX, true);

    public Semaphore getTorniquete() {
        return torniquete;
    }

    public int getAforoMax() {
        return AFORO_MAX;
    }
}