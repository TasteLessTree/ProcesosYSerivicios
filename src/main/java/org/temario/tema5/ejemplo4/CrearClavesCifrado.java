package org.temario.tema5.ejemplo4;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class CrearClavesCifrado {
    private SecretKey secretKey;
    private byte[] iv;

    public CrearClavesCifrado() throws Exception {
        this.secretKey = generarClave();
        this.iv = generarIV();
    }

    private SecretKey generarClave() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128); // También prodría haber sido 192 o 256
        return kg.generateKey();
    }

    private byte[] generarIV() {
        byte[] iv = new byte[16]; // Para AES se usan 16 bytes
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public byte[] getIv() {
        return iv;
    }
}