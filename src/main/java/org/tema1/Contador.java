package org.tema1;

public class Contador {
    public static void main(String[] args) {
        Contador contador = new Contador();

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        System.out.println(contador.sumar(x, y));
    }

    public int sumar(int num1, int num2) {
        int resultado = 0;

        for (int i = num1; i <= num2; i++) {
            resultado += i;
        }

        return resultado;
    }
}