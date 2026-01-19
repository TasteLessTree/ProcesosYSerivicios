package org.ejercicios.tema5;

import java.security.MessageDigest;

public class ValidadorCredenciales {
    // Verificar hashes
    public boolean esElMismoHash(String contenido) {
        // Obtener el hash
        HASHManager hashManager = new HASHManager();
        MessageDigest md = hashManager.getMessageDigest();

        return false;
    }
}