package org.temario.tema5.ejemplo5;

import org.temario.tema5.ejemplo4.CrearClavesCifrado;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class ServidorDesencriptador extends Thread {
    private ServerSocket servidor;
    private Socket cliente;
    private int puerto;
    private SecretKey llave;
    private byte[] iv;
    private BufferedReader entrada;

    public ServidorDesencriptador(String name, int puerto, CrearClavesCifrado creador) {
        super(name);
        this.puerto = puerto;
        this.llave = creador.getSecretKey();
        this.iv = creador.getIv();
    }

    // Iniciar el servidor
    public void iniciar() {
        try {
            System.out.println("SERVIDOR: Escuchando en el puerto: " + puerto);

            // Esperamos la conexión y la aceptamos
            servidor = new ServerSocket(puerto);

            while (true) {
                System.out.println("SERVIDOR: Esperando cliente...");
                Socket cliente = servidor.accept();

                // Conexión establecida
                InetAddress direccionCliente = cliente.getLocalAddress();
                String direccion = direccionCliente.getHostAddress();
                System.out.println("SERVIDOR: Cliente conectado desde /" + direccion);

                // Enviar respuesta al cliente
                start();
            }
        } catch (IOException e) {
            System.err.println("SERVIDOR: error en el servidor: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();
        try {
            System.out.println("HILO (" + hilo + "): Atendiendo al cliente.");
            // Enviar la salida al cliente
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            salida.println("El servidor dice hola (no encriptado).");

            // Leer entrada del cliente
            String mensaje = leerMensaje();
            System.out.println("SERVIDOR: mensaje recibido desde el cliente: \"" + mensaje + "\".");

            System.out.println("HILO (" + hilo + "): Finalizado.");

            // Cerrar el cliente y la salida
            salida.close();
            cliente.close();
        } catch (IOException e) {
            System.err.println("Error al enviar la salida al cliente: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("SERVIDOR: Error al desenciptar");
        }
    }

    // Descifrar el mensaje
    private String descifrarTexto(String cifrado, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(cifrado));
        return new String(original);
    }

    // Leer mensaje del cliente
    private String leerMensaje() throws Exception {
        return descifrarTexto(entrada.readLine(), llave, iv);
    }
}