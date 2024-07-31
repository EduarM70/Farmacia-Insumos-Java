package sv.edu.udb.servicio_rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/mensajes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensajes(){
        List<Mensaje> mensajes = new ArrayList();

        for (int i=0; i<=10; i++){
            Mensaje m = new Mensaje();
            m.setNumero(i);
            m.setMensaje("Mensaje # " + i );
            mensajes.add(m);
        }

        return Response.status(200).entity(mensajes).build();
    }
}

class Mensaje {
    private int numero;
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}