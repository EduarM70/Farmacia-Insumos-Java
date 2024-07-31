package sv.edu.udb.config;

import org.glassfish.jersey.servlet.internal.PersistenceUnitBinder;

import java.sql.*;

public abstract class ConnectionDB {
    private Connection conexion = null;
    public PreparedStatement statement = null;
    private ResultSet results = null;

    public ConnectionDB() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("ERROR: No encuentro el driver de la BD: "+ e.getMessage());
        }

    }

    public void getConnection() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia_dwf", "root", "");
    }

    public ResultSet GetResultsQuery(){
        return results;
    } // con este los obtienes los datos de la consulta

    public void setStatement(String query) throws SQLException { statement = conexion.prepareStatement(query); }

    public void setValues(int id, String value) throws SQLException { statement.setString(id, value); }

    public void setValues(int id, int value) throws SQLException { statement.setInt(id, value); }

    public void setValues(int id, double value) throws SQLException { statement.setDouble(id, value); }

    public void ExecuteQuery() throws SQLException{ statement.executeUpdate(); }

    public void ExecuteQueryResults(){ // con este metodo haces la consulta pero guardas los resultados

        try { this.results = statement.executeQuery(); }
        catch (SQLException error){ System.out.println("ERROR: Fallo en SQL: " + error.getMessage()); }

    }

    // Metodo que cierra la conexion
    public void closeConnection() throws SQLException{
        if (conexion != null){
            conexion.close();
            conexion = null;
        }

    }

}
