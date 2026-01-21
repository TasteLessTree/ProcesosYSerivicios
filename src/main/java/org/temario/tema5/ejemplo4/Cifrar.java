package org.temario.tema5.ejemplo4;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.FileOutputStream;
import java.util.Base64;

public class Cifrar {
    public static void cifrarTexto(String texto, SecretKey secretKey, byte[] iv, String filename) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(texto.getBytes());
        String encoded = Base64.getEncoder().encodeToString(encrypted);

        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(encoded.getBytes());
        }
    }
}