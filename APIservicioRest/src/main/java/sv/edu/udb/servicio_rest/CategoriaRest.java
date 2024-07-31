package sv.edu.udb.servicio_rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.DAO.CategoriasDAO;
import sv.edu.udb.models.Categoria;

import java.sql.SQLException;
import java.util.List;

@Path("/categorias")
public class CategoriaRest {

    CategoriasDAO categoriasDAO = null;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoria() throws SQLException {
        categoriasDAO = new CategoriasDAO();
        List<Categoria> categorias = categoriasDAO.findAll() ;
        return Response.status(200).entity(categorias).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumoById(@PathParam("id") int id) throws SQLException {
        categoriasDAO = new CategoriasDAO();

        Categoria categoria = categoriasDAO.findById(id);

        if (categoria == null){
            return Response.status(404).build();
        }

        return Response.status(200).entity(categoria).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCategoria(Categoria categoria) throws SQLException {

        CategoriasDAO categoriasDAO = new CategoriasDAO();
        categoriasDAO.insert(categoria);
        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(categoria).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response eliminarCategoria(@PathParam("id")  int id) throws SQLException {

        categoriasDAO = new CategoriasDAO();
        Categoria categoria = categoriasDAO.findById(id);

        if (categoria == null){
            return Response.status(400).header("Access-Controll-Allow-Origin", "*")
                    .entity("La categoria no existe").build();
        }

        categoriasDAO.delete(id);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(categoria).build();
    }

    // UPDATE
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateInsumo(@PathParam("id") int id, Categoria updatedCategoria) throws SQLException {
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        Categoria categoria = categoriasDAO.findById(id);

        if (categoriasDAO.findById(id) == null){
            return Response.status(400).header("Access-Controll-Allow-Origin", "*")
                    .entity("La categoria que ingresaste no existe: " + categoria.getNombre()).build();
        }

        categoria.setNombre(updatedCategoria.getNombre());
        categoria.setDescripcion(updatedCategoria.getDescripcion());

        categoriasDAO.update(categoria);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(categoria).build();

    }

}
