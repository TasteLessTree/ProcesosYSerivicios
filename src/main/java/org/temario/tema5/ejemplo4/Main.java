package org.temario.tema5.ejemplo4;

import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear claves
            CrearClavesCifrado creador = new CrearClavesCifrado();
            SecretKey secretKey = creador.getSecretKey();
            byte[] iv = creador.getIv();

            String textoOriginal = "Texto a cifrar";
            String filename = "src/main/java/org/temario/tema5/ejemplo4/cifrado.txt";

            // Cifrar
            Cifrar.cifrarTexto(textoOriginal, secretKey, iv, filename);
            System.out.println("Texto cifrado guardado en: " + filename);

            // Descifrar
            String textoDescifrado = Descifrar.descifrarTexto(secretKey, iv, filename);
            System.out.println("Texto descifrado en: " + textoDescifrado);
        } catch (Exception e) {
            System.err.println("Error con el proceso de encriptado/desencriptado: " + e.getMessage());
        }
    }
}