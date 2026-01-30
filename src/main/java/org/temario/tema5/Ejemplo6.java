package org.temario.tema5;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Ejemplo6 {
    // Generar un par de claves RSA de 2048 bytes
    public static KeyPair generarKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    // Cifrar un texto usando la clave pública (confidencialidad)
    public static String cifrar(String mensaje, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] mensajeBytes = mensaje.getBytes(StandardCharsets.UTF_8);
        byte[] bytesCifrados = cipher.doFinal(mensajeBytes);

        return Base64.getEncoder().encodeToString(bytesCifrados);
    }

    // Descifrar el texto usando la clave privada (confidencialidad)
    public static String desencriptar(String mensaje, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytesCifrados = Base64.getDecoder().decode(mensaje);
        byte[] bytesDescifrado = cipher.doFinal(bytesCifrados);

        return new String(bytesDescifrado, StandardCharsets.UTF_8);
    }

    // Firma un mensaje usando la clave privada (autentificación)
    public static String firmar(String mensaje, PrivateKey privateKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(mensaje.getBytes(StandardCharsets.UTF_8));
        byte[] firma = sig.sign();

        return Base64.getEncoder().encodeToString(firma);
    }

    // Verificar la firma de un mensaje usando la clave pública (autentificación)
    public static boolean verificarFirma(String mensaje, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(mensaje.getBytes(StandardCharsets.UTF_8));
        byte[] fima = Base64.getDecoder().decode(mensaje);

        return sig.verify(fima);
    }
}