package org.temario.tema2;

public class Ejemplo9 {
    static class TareaLarga implements Runnable {
        @Override
        public void run() {
            try {
                // El trabajador intenta dormir 10 segundos
                System.out.println("TRABAJADOR: Empezando tarea larga (10 segundos)...");
                Thread.sleep(10000); // 10,000 milisegundos
                // Si el 'sleep' termina sin ser interrumpido, se imprime esto:
                System.out.println("TRABAJADOR: Tarea completada con éxito.");
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido MIENTRAS duerme (o espera),
                // el 'sleep' se cancela y salta a este bloque 'catch'.
                System.out.println("TRABAJADOR: ¡Me han interrumpido! No pude terminar.");
                System.out.println("TRABAJADOR: Limpiando y saliendo...");
            }
        }
    }

    public static void main(String[] args) {
        // 1. Prepara la tarea y el hilo trabajador
        Runnable tareaLarga = new TareaLarga();
        Thread hiloTrabajo = new Thread(tareaLarga);

        // 2. El gerente (main) inicia al trabajador
        System.out.println("GERENTE (main): Empezad a trabajar. Os interrumpo en 2 segundos.");
        hiloTrabajo.start();
        // 3. El gerente (main) espera 2 segundos
        try {
            Thread.sleep(2000); // 2 segundos
        } catch (InterruptedException e) {
            // El hilo main también podría ser interrumpido, pero lo ignoramos
            System.out.println("Error en sleep: " + e.getMessage());
        }

        // 4. Después de 2s, el gerente envía la señal de interrupción
        System.out.println("GERENTE (main): ¡Tiempo! Enviando señal de interrupción.");
        hiloTrabajo.interrupt();
        System.out.println("GERENTE (main): Señal enviada. El hilo 'main' ha terminado.");
    }
}