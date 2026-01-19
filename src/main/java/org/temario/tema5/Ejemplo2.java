package org.temario.tema5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ejemplo2 {
    public static void main(String[] args) {
        try {
            String texto = "ContraseñaSeguraQueNadieDeberíaVer";

            // Obtenemos una instancia del algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Calcular el hash en una línea
            byte[] hashBytes = md.digest(texto.getBytes());

            // Calcular el hash haciendo un reset:
            /*md.reset();
            md.update(texto.getBytes());
            byte[] hashBytes = md.digest(texto.getBytes());*/

            String hashHex = String.format("%064x", new BigInteger(1, hashBytes));

            // Mostrar el resultado por consola:
            System.out.println("Texto original: " + texto);
            System.out.println("Hash original: " + hashHex);
        } catch (NoSuchAlgorithmException nsae) {
            System.err.println("Error al encriptar: " + nsae.getMessage());
            System.err.println("Es probable que el algoritmo no exita");
        }
    }
}