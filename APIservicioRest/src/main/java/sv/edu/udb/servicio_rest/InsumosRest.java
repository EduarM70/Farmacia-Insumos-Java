package sv.edu.udb.servicio_rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.udb.DAO.CategoriasDAO;
import sv.edu.udb.DAO.InsumosDAO;
import sv.edu.udb.models.Insumo;

import java.sql.SQLException;
import java.util.List;

@Path("insumos")
public class InsumosRest {

    InsumosDAO insumosDAO = null;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumos() throws SQLException {
        insumosDAO = new InsumosDAO();
        List<Insumo> insumos = insumosDAO.findAll();

        return Response.status(200).entity(insumos).build();
    }
    /* Obtener un insumo por el ID */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumoById(@PathParam("id") int id) throws SQLException {
        insumosDAO = new InsumosDAO();

        Insumo insumo = insumosDAO.findById(id);

        if (insumo == null){
            return Response.status(404).build();
        }

        return Response.status(200).entity(insumo).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertInsumo(Insumo insumo) throws SQLException {
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        InsumosDAO insumosDAO = new InsumosDAO();

        if (categoriasDAO.findById(insumo.getCategoriaId()) == null){ // consultar si existe la categoria del nuevo insumo
            return Response.status(400).header("Access-Controll-Allow-Origin", "*")
                    .entity("La categoria que ingresaste no existe").build();
        }

        insumosDAO.insert(insumo);

        return Response.status(201).header("Access-Control-Allow-Origin", "*").entity(insumo).build();
    }

    // @DELETE
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response eliminarInsumo(@PathParam("id")  int id) throws SQLException {

        InsumosDAO insumosDAO = new InsumosDAO();
        Insumo insumo = insumosDAO.findById(id);

        if (insumo == null){
            return Response.status(400).header("Access-Controll-Allow-Origin", "*")
                    .entity("El insumo no existe").build();
        }

        insumosDAO.delete(id);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(insumo).build();
    }

    // UPDATE 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateInsumo(@PathParam("id") int id, Insumo updatedInsumo) throws SQLException {
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        InsumosDAO insumosDAO = new InsumosDAO();
        Insumo insumo = insumosDAO.findById(id);

        if (insumo == null){
            return Response.status(404).header("Access-Control-Allow-Origin", "*").entity("El insumo no existe").build();
        }

        if (categoriasDAO.findById(updatedInsumo.getCategoriaId()) == null){
            return Response.status(400).header("Access-Controll-Allow-Origin", "*")
                    .entity("La categoria que ingresaste no existe").build();
        }

        insumo.setNombre(updatedInsumo.getNombre());
        insumo.setDescripcion(updatedInsumo.getDescripcion());
        insumo.setCantidad(updatedInsumo.getCantidad());
        insumo.setPrecio(updatedInsumo.getPrecio());
        insumo.setFecha_vencimiento(updatedInsumo.getFecha_vencimiento());
        insumo.setCategoriaId(updatedInsumo.getCategoriaId());

        insumosDAO.update(insumo);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(insumo).build();

    }

}
