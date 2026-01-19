package org.ejercicios.tema4.servidor;

import org.ejercicios.tema4.InterfazCalculadora;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class App {
    public static void registrarCalculadora() {
        try {
            Registry registry = null;
            registry = LocateRegistry.createRegistry(5555);

            Calculadora calculadora = new Calculadora();
            registry.bind("Calculadora", (InterfazCalculadora) UnicastRemoteObject.exportObject(calculadora, 0));
            System.out.println("Servidor arrancando...");
        } catch (RemoteException re) {
            System.out.println("Error al registrar la calculadora: " + re.getMessage());
            re.printStackTrace();
        } catch (AlreadyBoundException abe) {
            System.out.println("Error al unir la calculadora: " + abe.getMessage());
            abe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        registrarCalculadora();
    }
}