package org.temario.tema3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
    private static final int puerto = 67;
    private static final String RUTA = "src/main/java/org/tema3/salida.txt";

    private byte[] buffer = new byte[1024];

    public ServidorUDP() {
    }

    public void iniciar() {
        File file =  new File(RUTA);

        if (file.exists()) {
            file.delete();
            System.out.println("Se ha eliminado");
        }

        try (DatagramSocket servidorUDP = new DatagramSocket(puerto)) {
            System.out.println("Iniciando ServidorUDP...");

            for (int i = 0; i < 10_000; i++) {
                // Paquete a recibir (buffer vacío):
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                // Recibir (bloquea hasta que el servidor devuelva algo)
                servidorUDP.receive(peticion);

                // Extraer información
                String mensaje = new String(peticion.getData(), 0, peticion.getLength());

                if (mensaje.equals("FIN")) {
                    System.out.println("ServidorUDP finalizando...");
                    return;
                } else {
                    escribirFichero(mensaje);
                }

                // Obtener la dirección del remitente
                DatagramPacket respuesta = obtenerPaqueteDatagram(peticion, mensaje);

                // Eviamos la respuesta
                servidorUDP.send(respuesta);
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor UDP: " + e.getMessage());
        }
    }

    private static DatagramPacket obtenerPaqueteDatagram(DatagramPacket peticion, String mensaje) {
        InetAddress direccion = peticion.getAddress();
        int puertoCliente = peticion.getPort();

        // Responder al cliente
        String mensajeRespuesta = "(ServidorUDP) Mensaje recibido: " + mensaje;
        byte[] bufferRespuesta = mensajeRespuesta.getBytes();

        // Crear el paquete para enviar
        // Datos, longitud, IP de destino, puerto destino
        return new DatagramPacket(
                bufferRespuesta,
                bufferRespuesta.length,
                direccion,
                puertoCliente
        );
    }

    private void escribirFichero(String mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA, true))) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ServidorUDP servidorUDP = new ServidorUDP();
        servidorUDP.iniciar();
    }
}