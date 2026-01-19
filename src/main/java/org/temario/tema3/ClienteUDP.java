package org.temario.tema3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    private static final String HOST = "localhost";
    private static final int PORT = 67;

    public ClienteUDP() {
    }

    public void iniciar() {
        try (DatagramSocket clienteUDP = new DatagramSocket()) {
            for (int i = 0; i <= 10_000; i++) {
                String mensaje = "";

                if (i == 10_000) {
                    mensaje = "FIN";
                } else {
                    mensaje = "Mensaje: " + i;
                }

                enviarMensaje(clienteUDP, mensaje);

                // Recibir la respuesta
                byte[] respuestaBuffer = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(respuestaBuffer, respuestaBuffer.length);
                clienteUDP.receive(respuesta);
            }
        } catch (IOException e) {
            System.out.println("Error con el cliente UDP: " + e.getMessage());
        }
    }

    private static void enviarMensaje(DatagramSocket cliente, String mensaje) throws IOException {
        byte[] buffer = mensaje.getBytes();

        // Obtener IP del servidor
        InetAddress direccion = InetAddress.getByName(HOST);

        // Creamos el paquete que vamos a enviar
        DatagramPacket pregunta = new DatagramPacket(
                buffer,
                buffer.length,
                direccion,
                PORT
        );

        cliente.send(pregunta);
    }

    public static void main(String[] args) {
        ClienteUDP clienteUDP = new ClienteUDP();
        clienteUDP.iniciar();
    }
}