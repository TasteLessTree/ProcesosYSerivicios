package org.calcu_distribuida.servidor;

import org.calcu_distribuida.InterfazCalculadora;

import java.rmi.RemoteException;

public class Calculadora implements InterfazCalculadora {
    @Override
    public float sumar(float num1, float num2) throws RemoteException {
        return num1 + num2;
    }

    @Override
    public float restar(float num1, float num2) throws RemoteException {
        return num1 - num2;
    }

    @Override
    public float multiplicar(float num1, float num2) throws RemoteException {
        return num1 * num2;
    }

    @Override
    public float dividir(float num1, float num2) throws RemoteException {
        if (num2 == 0) {
            throw new ArithmeticException("División por cero");
        }
        return num1 / num2;
    }
}