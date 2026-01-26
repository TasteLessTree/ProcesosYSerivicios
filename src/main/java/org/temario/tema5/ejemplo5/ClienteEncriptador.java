package org.temario.tema5.ejemplo5;

import org.temario.tema5.ejemplo4.CrearClavesCifrado;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

public class ClienteEncriptador {
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private String host;
    private int puerto;
    private SecretKey llave;
    private byte[] iv;

    public ClienteEncriptador(String host, int puerto, CrearClavesCifrado creador) {
        this.host = host;
        this.puerto = puerto;
        this.llave = creador.getSecretKey();
        this.iv = creador.getIv();
    }

    public void iniciar() {
        try {
            System.out.println("CLIENTE: Conectando al servidor...");

            // Nos conectamos
            cliente = new Socket(host, puerto);
            System.out.println("CLIENTE: Conectado. Esperando resultado del proceso...");

            // Abrimos los canales de texto
            abrirCanales();

            // Enviar mensaje al servidor
            escribirMensaje("Hola, cómo un mensaje cifrado.");

            // Leemos la salida
            String mensaje = leerMensaje();
            System.out.println("CLIENTE: El servidor ha terminado la tarea en " + mensaje + " segundos.");

            // Cerramos canales de texto y la conexión
            cerrarCanales();
            parar();
        } catch (IOException e) {
            System.err.println("CLIENTE: Error en el cliente: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("CLIENTE: Error al encriptar el mensaje: " + e.getMessage());
        }
    }

    private void abrirCanales() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(cliente.getOutputStream(), true);
    }

    private void escribirMensaje(String mensaje) throws Exception {
        salida.println(cifrarTexto(mensaje, llave, iv));
    }

    private String leerMensaje() throws IOException {
        return entrada.readLine();
    }

    private void cerrarCanales() throws IOException {
        if (entrada != null) entrada.close();
        if (salida != null) salida.close();
    }

    private void parar() throws IOException {
        if (cliente != null) cliente.close();
    }

    // Cifrar texto
    private String cifrarTexto(String texto, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}