package org.tema4;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Scraping {
    public static void main(String[] args) {
        // URL objetivo
        String URLobjetivo = "https://example.com";
        String salida = "C:\\Users\\AndrésPérezM\\IdeaProjects\\ProcesosYSerivicios\\src\\main\\java\\org\\tema4\\salida_scraping\\Salida.html";

        try {
            // Crear el objeto URL
            URL url = new URL(URLobjetivo);

            // Abrir la conexión y castear a HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la petición
            connection.setRequestMethod("GET");
            // Asegurarse de que la web responda
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Verificar el código de respuesta
            int codigoRespuesta = connection.getResponseCode();
            System.out.println("Respuesta: " + codigoRespuesta);

            // 200 (éxito)
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                // Leer la web y escribir en un archivo
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(salida))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                }
            }

            // Desconectarse
            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Ocurrió un error de conexión o escritura: " + e.getMessage());
        }
    }
}