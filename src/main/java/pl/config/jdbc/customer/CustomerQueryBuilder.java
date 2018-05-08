package pl.config.jdbc.customer;

import pl.config.jdbc.JdbcConnector;
import pl.config.jdbc.JdbcManager;
import pl.politechnika.beans.customer.CustomerBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerQueryBuilder {

    public static void saveCustomer(CustomerBean customerBean) {
        try {
            PreparedStatement preparedStatement = JdbcConnector.getInstance().openConnection().prepareStatement("INSERT INTO customers ( " +
                    "discount_code, zip, name, addressline1, addressline2, city, state, phone, fax, email, credit_limit) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, customerBean.getDiscountCode());
            preparedStatement.setString(2, customerBean.getZip());
            preparedStatement.setString(3, customerBean.getName());
            preparedStatement.setString(4, customerBean.getAddressLine1());
            preparedStatement.setString(5, customerBean.getAddressLine2());
            preparedStatement.setString(6, customerBean.getCity());
            preparedStatement.setString(7, customerBean.getState());
            preparedStatement.setString(8, customerBean.getPhone());
            preparedStatement.setString(9, customerBean.getFax());
            preparedStatement.setString(10, customerBean.getEmail());
            preparedStatement.setString(11, customerBean.getCreditLimit());
            JdbcManager.insert(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
