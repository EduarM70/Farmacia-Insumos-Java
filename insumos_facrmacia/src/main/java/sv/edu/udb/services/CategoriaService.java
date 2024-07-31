package sv.edu.udb.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sv.edu.udb.models.Categoria;
import sv.edu.udb.models.Insumo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class CategoriaService {
    private static final String BASE_URL = "http://localhost:8081/servicio_rest_war_exploded/api/categorias";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<Categoria> getAllCategorias() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<ArrayList<Categoria>>() {});
        } else {
            throw new RuntimeException("Failed to fetch categoria: " + response.statusCode());
        }
    }

    public Categoria getOneCategoria(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), Categoria.class);
        } else {
            throw new RuntimeException("Failed to fetch categorias: " + response.statusCode());
        }
    }

    // Servicio para crear una nueva categoria
    public void createCategoria(Categoria categoria) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(categoria);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    // Servicios para actualzizar insumo
    public void updateCategoria(int id, Categoria categoria) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(categoria);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    // Servicio para eliminar un servicio
    public void deleteCategoria(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/delete/" + id))
                .DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
