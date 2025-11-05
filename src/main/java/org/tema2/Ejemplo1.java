package org.tema2;

public class Ejemplo1 {
    public static void main(String[] args) {
        MiHilo miHilo = new MiHilo();
        miHilo.start();
        System.out.println("El hilo principal se sigue ejecutando");
    }
}