package org.ejercicios.tema5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HASHManager {
    private final static String ALGORITMO = "SHA-256";

    // Encriptar
    public String encriptar(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITMO);

            md.reset();
            md.update(texto.getBytes());
            byte[] hash = md.digest(texto.getBytes());

            return String.format("%064x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al encriptar: " + e.getMessage());
            return null;
        }
    }

    // Devoler el digest
    public MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(ALGORITMO);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al obtener el digest: " + e.getMessage());
            return null;
        }
    }
}