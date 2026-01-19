package org.ejercicios.tema4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazCalculadora extends Remote {
    public float sumar(float num1, float num2) throws RemoteException;
    public float restar(float num1, float num2) throws RemoteException;
    public float multiplicar(float num1, float num2) throws RemoteException;
    public float dividir(float num1, float num2) throws RemoteException;
}