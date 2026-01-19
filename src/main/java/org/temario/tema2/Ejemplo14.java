package org.temario.tema2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Ejemplo14 {
    private static final int[] esperas = {200, 300, 100, 400, 0};

    // Creamos el puente donde solo puede pasar un coche
    private final BlockingQueue<String> puente = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        Thread productor = new Thread(() -> {
            try {
                for (int i = 0; i < esperas.length; i++) {
                    String vehiculo = "Vehículo-" + (i + 1);

                    System.out.println(vehiculo + " -> está esperando a cruzar el puente");
                    //puente.put(vehiculo);
                    Thread.sleep(esperas[i]);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error al cruzar el puente: " + e.getMessage());
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 0; i < esperas.length; i++) {
                    //String vehiculo = puente.take();

                    //System.out.println(vehiculo + " -> ha cruzado el puente");
                    Thread.sleep(esperas[i]);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error al consumir el puente: " + e.getMessage());
            }
        });

        productor.start();
        consumidor.start();
    }
}