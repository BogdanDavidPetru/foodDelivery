package persistent;

import business.model.Products;
import database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class ProductGateaway extends AbstractGateaway<Products>{

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM products");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public Products findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("productname");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Products product = new Products(resultSet.getInt("id"),resultSet.getString("productName"), resultSet.getFloat("price"),resultSet.getInt("quantity"), resultSet.getString("description"));
                return product;
            }
            return null;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Product DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
