package org.tema4.appdistribuida;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazDefiniciones extends Remote {
    public String devolverDefinicion(String palabra) throws RemoteException;
}