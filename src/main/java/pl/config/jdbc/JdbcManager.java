package pl.config.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class JdbcManager {
    private static JdbcConnector connector = JdbcConnector.getInstance();

    public static ResultSet query(String query) {
        try {
            Connection connection = connector.openConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            connection.close();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            connector.rollback();
            connector.closeConnection();
            return null;
        }
    }

    public static void insert(PreparedStatement preparedStatement) {
        try {
            connector.openConnection();
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            connector.rollback();
            connector.closeConnection();
        }
    }
}
