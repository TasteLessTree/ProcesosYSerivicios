package org.tema5;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Set;

public class CriptografiaEjemplo1 {
    public static void main(String[] args) {
        // Definir el tipo de algoritmo con MessageDigest
        final String TIPO_MENSSAGE_DIGEST = MessageDigest.class.getSimpleName();

        // Se obtiene la lista de los proveedores de seguridad instalados
        Provider[] providers = Security.getProviders();

        for (Provider provider : providers) {
            // Por cada proveedor se obtiene el conjunto de servicios
            Set<Service> services = provider.getServices();

            for (Service service : services) {
                if (service.getType().equals(TIPO_MENSSAGE_DIGEST)) {
                    System.out.println(service.getAlgorithm());
                }
            }
        }
    }
}