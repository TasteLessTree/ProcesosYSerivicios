package org.temario.tema5.ejemplo5;

import org.temario.tema5.ejemplo4.CrearClavesCifrado;

public class Main {
    public static void main(String[] args) {
        try {
            CrearClavesCifrado creador = new CrearClavesCifrado();

            ClienteEncriptador cliente = new ClienteEncriptador("localhost", 42069, creador);
            ServidorDesencriptador servidor = new ServidorDesencriptador("localhost", 42069, creador);

            //servidor.iniciar();
            cliente.iniciar();
        } catch (Exception e) {
            System.out.println("Error al encriptar o desencriptar: " + e.getMessage());
        }
    }
}