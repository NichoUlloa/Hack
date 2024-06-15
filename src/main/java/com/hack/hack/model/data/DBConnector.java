package com.hack.hack.model.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final DBConnector INSTANCE = new DBConnector();
    private static Connection connection = null;

    public static DBConnector getInstance() {
        return INSTANCE;
    }

    // Realiza la conexión para MySQL sin una base de datos específica
    public static Connection connection(String username, String password) throws ClassNotFoundException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = doConnection("", username, password);
            }
        } catch (SQLException e) {
            System.err.println("Error al comprobar si está cerrada la conexión: " + e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    // Realiza la conexión para una base de datos SQL específica
    public static Connection connection(String db, String username, String password) {
        try {
            if (connection == null || connection.isClosed()) {
                connection = doConnection(db, username, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al comprobar si está cerrada la conexión: " + e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    // Cierra el nodo de conexión
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Conexión cerrada.");
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e);
        }
    }

    // Realiza la conexión a la base de datos
    private static Connection doConnection(String db, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL = URL + db;
        return DriverManager.getConnection(dbURL, username, password);
    }
}
