package org.tema2;

// Ejemplo 6
public class Vecino extends Thread {
    private final Piscina piscina;

    public Vecino(String nombre, Piscina piscina) {
        super(nombre);
        this.piscina = piscina;
    }

    @Override
    public void run() {
        piscina.usarPiscina();
    }
}