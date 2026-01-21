package org.temario.tema5.ejemplo4;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Descifrar {
    public static String descifrarTexto(SecretKey secretKey, byte[] iv, String filename) throws Exception {
        byte[] encryptedData = Files.readAllBytes(Paths.get(filename));
        String encodedData = new String(encryptedData);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encodedData));
        return new String(original);
    }
}