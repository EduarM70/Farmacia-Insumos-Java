package sv.edu.udb.DAO;

import sv.edu.udb.config.ConnectionDB;
import sv.edu.udb.models.Categoria;
import sv.edu.udb.models.Insumo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriasDAO extends ConnectionDB {
    private String query = "";
    private ResultSet results = null;


    public CategoriasDAO() throws SQLException {
    }

    public void insert(Categoria categoria) throws SQLException {
        this.getConnection();
        query = "insert into categorias (nombre, descripcion) VALUES (?,?)";
        this.setStatement(query);
        this.setValues(1, categoria.getNombre());
        this.setValues(2, categoria.getDescripcion());

        this.ExecuteQuery();
        this.closeConnection();
    }

    public void update(Categoria categoria) throws SQLException {
        this.getConnection();

        query = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
        this.setStatement(query);
        this.setValues(1, categoria.getNombre());
        this.setValues(2, categoria.getDescripcion());
        this.setValues(3, categoria.getId());

        this.ExecuteQuery();
        this.closeConnection();

    }

    public void delete(int id) throws SQLException {
        this.getConnection();

        query = "DELETE FROM categorias WHERE id = ?";
        this.setStatement(query);
        this.setValues(1, id);
        this.ExecuteQuery();
        this.closeConnection();
    }

    public ArrayList<Categoria> findAll() throws SQLException {
        this.getConnection();

        query = "SELECT * FROM categorias";
        this.setStatement(query);
        this.ExecuteQueryResults();
        results = this.GetResultsQuery();

        ArrayList<Categoria> categorias = new ArrayList();

        while (results.next()){
            Categoria tmp = new Categoria();
            tmp.setId(results.getInt(1));
            tmp.setNombre(results.getNString(2));
            tmp.setDescripcion(results.getNString(3));

            categorias.add(tmp);
        }
        this.closeConnection();
        return categorias;
    }

    public Categoria findById(int id) throws SQLException {
        Categoria categoria = null;

        getConnection();
        query = "SELECT * FROM categorias WHERE id = ?";
        this.setStatement(query);
        this.setValues(1, id);
        this.ExecuteQueryResults();

        results = GetResultsQuery();
        while (results.next()){
            categoria = new Categoria();
            categoria.setId(results.getInt(1));
            categoria.setNombre(results.getNString(2));
            categoria.setDescripcion(results.getNString(3));
        }

        this.closeConnection();
        return categoria;
    }
}
