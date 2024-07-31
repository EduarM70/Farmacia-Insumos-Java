package sv.edu.udb.DAO;

import sv.edu.udb.config.ConnectionDB;
import sv.edu.udb.models.Categoria;
import sv.edu.udb.models.Insumo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsumosDAO extends ConnectionDB {

    private String query = "";
    private ResultSet results = null;

    public InsumosDAO() throws SQLException {
    }

    public void insert(Insumo insumo) throws SQLException {
        this.getConnection();
        query = "insert into insumos (nombre, descripcion, cantidad, precio, fecha_de_expiracion, categoria_id) VALUES (?,?,?,?,?, ?)";
        this.setStatement(query);
        this.setValues(1, insumo.getNombre());
        this.setValues(2, insumo.getDescripcion());
        this.setValues(3, insumo.getCantidad());
        this.setValues(4, insumo.getPrecio());
        this.setValues(5, insumo.getFecha_vencimiento());
        this.setValues(6, insumo.getCategoriaId());

        this.ExecuteQuery();
        this.closeConnection();
    }

    public void update(Insumo insumo) throws SQLException {
        this.getConnection();

        query = "UPDATE insumos SET nombre = ?, descripcion = ?, cantidad = ?, precio = ?, fecha_de_expiracion = ?, categoria_id = ? WHERE id = ?";
        this.setStatement(query);
        this.setValues(1, insumo.getNombre());
        this.setValues(2, insumo.getDescripcion());
        this.setValues(3, insumo.getCantidad());
        this.setValues(4, insumo.getPrecio());
        this.setValues(5, insumo.getFecha_vencimiento());
        this.setValues(6, insumo.getCategoriaId());
        this.setValues(7, insumo.getId());
        this.ExecuteQuery();
        this.closeConnection();

    }

    public void delete(int id) throws SQLException {
        this.getConnection();

        query = "DELETE FROM insumos WHERE id = ?";
        this.setStatement(query);
        this.setValues(1, id);
        this.ExecuteQuery();
        this.closeConnection();
    }

    public ArrayList<Insumo> findAll() throws SQLException {
        this.getConnection();

        query = "SELECT ins.id, ins.nombre, ins.descripcion, ins.cantidad, ins.precio, ins.fecha_de_expiracion, cat.nombre FROM insumos ins  INNER JOIN categorias cat ON ins.categoria_id =cat.id";
        this.setStatement(query);
        this.ExecuteQueryResults();
        results = this.GetResultsQuery();

        ArrayList<Insumo> insumos = new ArrayList();

        while (results.next()){
            Insumo tmp = new Insumo();
            tmp.setId(results.getInt(1));
            tmp.setNombre(results.getString(2));
            tmp.setDescripcion(results.getString(3));
            tmp.setCantidad(results.getInt(4));
            tmp.setPrecio(results.getDouble(5));
            tmp.setFecha_vencimiento(results.getString(6));
            tmp.setCategoria(results.getString(7));

            insumos.add(tmp);
        }
        this.closeConnection();
        return insumos;
    }

    public Insumo findById(int id) throws SQLException {
        Insumo insumo = null;

        getConnection();
        query = "SELECT ins.id, ins.nombre, ins.descripcion, ins.cantidad, ins.precio, ins.fecha_de_expiracion, cat.nombre FROM insumos ins  INNER JOIN categorias cat ON ins.categoria_id =cat.id WHERE ins.id = ?";
        this.setStatement(query);
        this.setValues(1, id);
        this.ExecuteQueryResults();
        results = GetResultsQuery();
        while (results.next()){
            insumo = new Insumo();
            insumo.setId(results.getInt(1));
            insumo.setNombre(results.getString(2));
            insumo.setDescripcion(results.getString(3));
            insumo.setCantidad(results.getInt(4));
            insumo.setPrecio(results.getDouble(5));
            insumo.setFecha_vencimiento(results.getString(6));
            insumo.setCategoria(results.getString(7));
        }

        this.closeConnection();
        return insumo;
    }
}
