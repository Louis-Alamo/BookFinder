package com.bookfinder.bookfinder.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Configurar para seguir redirecciones
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Java HttpClient") // Añadir un User-Agent
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de la API", e);
        }

        // Verifica el estado de la respuesta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error: Estado de respuesta no es 200, es " + response.statusCode());
        }

        return response.body();
    }
}
