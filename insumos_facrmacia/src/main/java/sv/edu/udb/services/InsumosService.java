package sv.edu.udb.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sv.edu.udb.models.Insumo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class InsumosService {

    private static final String BASE_URL = "http://localhost:8081/servicio_rest_war_exploded/api/insumos";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<Insumo> getAllInsumos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), new TypeReference<ArrayList<Insumo>>() {});
        } else {
            throw new RuntimeException("Failed to fetch insumos: " + response.statusCode());
        }
    }

    // Obtener un insumo por el id
    public Insumo getOneInsumo(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), Insumo.class);
        } else {
            throw new RuntimeException("Failed to fetch insumos: " + response.statusCode());
        }
    }

    // Servicio para crear un nuevo insumo
    public void createInsumo(Insumo insumo) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(insumo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json").
                POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    // Servicios para actualzizar insumo
    public void updateInsumo(int id, Insumo insumo) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(insumo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // Servicio para eliminar un servicio
    public void deleteInsumo(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/delete/" + id))
                .DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
