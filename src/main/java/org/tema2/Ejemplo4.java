package org.tema2;

public class Ejemplo4 extends Thread {
    public Ejemplo4(String nombre) {
        super(nombre);
    }

    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Hilo: " + this.getName() + " - i = " + i);
        }
    }

    public static void main(String[] args) {
        Ejemplo4 hilo = new Ejemplo4("Hilo1");
        Ejemplo4 hilo2 = new Ejemplo4("Hilo2");
        Ejemplo4 hilo3 = new Ejemplo4("Hilo3");

        hilo.start();
        hilo2.start();
        hilo3.start();
    }
}