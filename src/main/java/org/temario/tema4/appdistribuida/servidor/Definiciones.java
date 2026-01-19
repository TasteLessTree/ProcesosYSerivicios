package org.temario.tema4.appdistribuida.servidor;

import org.temario.tema4.appdistribuida.InterfazDefiniciones;

import java.rmi.RemoteException;
import java.util.HashMap;

public class Definiciones implements InterfazDefiniciones {
    public HashMap<String, String> diccionario = new HashMap<>();

    @Override
    public String devolverDefinicion(String palabra) throws RemoteException {
        diccionario.put("fuselaje", "parte central del avión");
        diccionario.put("alas", "sirven para volar");
        diccionario.put("empenaje", "no se que es eso");
        diccionario.put("tren de aterrizaje", "las ruedas para aterrizar o navegar por las pistas");
        diccionario.put("motores", "sin ellos, el avión no vuela");

        if (diccionario.containsKey(palabra.toLowerCase())) {
            return diccionario.get(palabra.toLowerCase());
        }

        return "No existe esa palabra";
    }
}