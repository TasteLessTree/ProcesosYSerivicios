package org.tema4.appdistribuida.cliente;

import org.tema4.appdistribuida.InterfazDefiniciones;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    private InterfazDefiniciones definiciones = null;

    public Cliente() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 42069);
            definiciones = (InterfazDefiniciones) registry.lookup("Definiciones");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        String definicion;

        try {
            definicion = cliente.definiciones.devolverDefinicion("fuselaje");
            System.out.println(definicion);
        } catch (RemoteException re) {
            System.out.println("Ha ocurrido un error: " + re.getMessage());
        }
    }
}