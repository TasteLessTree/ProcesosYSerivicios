package org.tema2;

public class Ejemplo6 {
    public static void main(String[] args) {
        Piscina piscina = new Piscina();
        int numVecionos = 7;

        System.out.println("Empieza el día de piscina: " + numVecionos);

        for (int i = 0; i < numVecionos; i++) {
            String nombre = "Vecino" + (i + 1);
            Vecino vecino = new Vecino(nombre, piscina);
            vecino.start();
        }
    }
}