package org.calcu_distribuida.cliente;

import org.calcu_distribuida.InterfazCalculadora;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    private InterfazCalculadora calculadora = null;

    public Cliente() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5555);
            calculadora = (InterfazCalculadora) registry.lookup("Calculadora");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error insesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();

        float resultado;

        try {
            resultado = cliente.calculadora.sumar(34.5f, 34.5f);
            System.out.println("Resultado: " + resultado);
        } catch (RemoteException e) {
            System.out.println("Ha ocurrido un error insesperado: " + e.getMessage());
        }
    }
}
