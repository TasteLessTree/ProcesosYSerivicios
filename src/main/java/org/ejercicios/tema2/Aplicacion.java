package org.ejercicios.tema2;

public class Aplicacion {
    public static void main(String[] args) {
        PoolConexionesDB conexiones = new PoolConexionesDB();
        int numUsuarios = 8;

        System.out.println("Iniciando el programa...");

        for (int i = 1; i <= numUsuarios; i++) {
            UsuarioDB usuarioDB = new UsuarioDB("Usuario-" + i, conexiones);
            usuarioDB.start();

            // Pequeña pausa para mejorar la simulación
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo sleep main: " + e.getMessage());
            }
        }
    }
}