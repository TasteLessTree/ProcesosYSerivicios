package org.temario.tema5.ejemplo5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncriptacionManager {
    private static final String CLAVE_BASE_64 = "RY1ehQNnBlZMrh1KTcHTFcITSMKjpJQqL76DNseKiic=";
    private static final String IV_BASE_64 = "Pcca9czV1dbnQRt7UALAdw==";

    public static String encriptar(String texto) {
        try {
            // Reconstruir clave
            byte[] claveBytes = Base64.getDecoder().decode(CLAVE_BASE_64);
            SecretKey key = new SecretKeySpec(claveBytes, "AES");

            // Reconstruir IV
            byte[] ivBytes = Base64.getDecoder().decode(IV_BASE_64);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            // Descifrar
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] textoCifrado = cipher.doFinal(texto.getBytes());

            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (Exception e) {
            System.err.println("Error al encriptar: " + e.getMessage());
            return null;
        }
    }

    public static String desencriptar(String texto) {
        try {
            // Reconstruir clave
            byte[] claveBytes = Base64.getDecoder().decode(CLAVE_BASE_64);
            SecretKey key = new SecretKeySpec(claveBytes, "AES");

            // Reconstruir IV
            byte[] ivBytes = Base64.getDecoder().decode(IV_BASE_64);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            // Descifrar
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] textoCifrado = Base64.getDecoder().decode(texto);
            byte[] textoDescifrado = cipher.doFinal(textoCifrado);

            return new String(textoDescifrado);
        } catch (Exception e) {
            System.err.println("Error al encriptar: " + e.getMessage());
            return null;
        }
    }
}