package persistent;

import business.model.Customers;
import database.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CustomerGateaway extends AbstractGateaway<Customers> {

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM customers");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public Customers findByUser(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("username");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Customers client = new Customers(resultSet.getInt("id"),resultSet.getString("address"),resultSet.getString("name"),resultSet.getString("pnc"), resultSet.getBoolean("loyal"),resultSet.getString("username"), resultSet.getString("password"));//new Customers(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("address"));
                return client;
            }
            return null;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Customer DAO:findByUser " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
