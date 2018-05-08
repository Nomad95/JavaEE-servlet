package pl.config.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static pl.config.jdbc.JDBCConnection.*;

@SuppressWarnings("all")
public class JdbcConnector {

    private static Connection connection;

    private static JdbcConnector ourInstance = new JdbcConnector();

    public static JdbcConnector getInstance() {
        return ourInstance;
    }

    private JdbcConnector() {
    }

    public Connection openConnection() {
        try {
            connection = DriverManager.getConnection(
                    DATABASE_URL, DATABASE_USERNAME,
                    DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection was closed already!");
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            if (!connection.isClosed()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
