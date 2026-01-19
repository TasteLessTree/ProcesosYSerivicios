package org.temario.tema4.appdistribuida.servidor;

import org.temario.tema4.appdistribuida.InterfazDefiniciones;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static void registrarDefiniciones() {
        try {
            Registry registry = LocateRegistry.createRegistry(42069);

            Definiciones definiciones = new Definiciones();
            registry.bind("Definiciones", (InterfazDefiniciones) UnicastRemoteObject.exportObject(definiciones, 0));

            System.out.println("Servidor arrandcando...");
        } catch (RemoteException re) {
            System.out.println("Error con el servidor: " + re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.out.println("Error con el servidor: " + abe.getMessage());
        }
    }

    public static void main(String[] args) {
        registrarDefiniciones();
    }
}