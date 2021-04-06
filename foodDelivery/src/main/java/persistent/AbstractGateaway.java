package persistent;

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


public abstract class AbstractGateaway<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractGateaway.class.getName());
    /**
     * The class type which depends on T-> in our application, this type is
     * either Clients, Products, Orders or ClientSum, basically the entities that
     * have a correspondent dataBase table.
     */
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    /**
     * The constructor
     */
    public AbstractGateaway() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * private method used to create the general form o a select query, to be later used
     * in a public method
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * private method used to create the general form o a delete query, to be later used
     * in the delete method
     * @param field
     * @return
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * private method used to create the general form o a insert query, to be later used
     * in the insert method
     * @return
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append("INTO ");
        sb.append(type.getSimpleName());
        //sb.append(" WHERE " + field + " =?");
        sb.append(" ( ");
        for(Field field: type.getDeclaredFields()) {
            if(!field.getName().contentEquals("ID")) {
                sb.append(field.getName() +", ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(") ");
        sb.append(" VALUES (?");
        for(int i=2;i<type.getDeclaredFields().length;i++)
            sb.append(", ?");
        sb.append(")");
        return sb.toString();
    }

    private String createUpdateQuery(String fieldToSelectWith) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");// + fieldToChange);
        for(Field field: type.getDeclaredFields()) {
            if(!field.getName().contentEquals("ID")) {
                sb.append(field.getName()); //+", ");
                sb.append(" =?, ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE " + fieldToSelectWith);
        sb.append(" =?");
        return sb.toString();
    }
    /**
     * The method findAll which returns all the entries from a specific entity,
     * that entity being represented by T so it is a generic method
     * @return
     */
    public List<T> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        String query = sb.toString();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * A method that returns the entry with the specified id from the specific
     * table given by entity T so it is a generic method. It also uses the private
     * createSelecTQuery method
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.isBeforeFirst())
                return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * private method which creates objects out of the resultSet returned by the dataBase
     * query. This method was built using the reflection technique, also being a
     * generic method. It creates objects of the specific type it was called on.
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                @SuppressWarnings("deprecation")
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * This public method, also a generic one, insert a specific type of object into
     * its own correspondent table, using the private createInsertQuery method
     * @param t
     * @return
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=this.createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i=1;
            for (Field field : type.getDeclaredFields())
                if(!field.getName().equals("ID")) {
                    field.setAccessible(true);
                    statement.setObject(i++,field.get(t));
                }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }
    /**
     * This method wasn't implemented here as in our application, the only places
     * where updates can take place are the quantity of products(in Products table)
     * and the sum in ClientSum table. Thus, the method was specifically implemented
     * for those table in their specific DataBase access classes.
     * @param t
     * @return
     */
    public boolean update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for (Field field : type.getDeclaredFields())
                if(!field.getName().equals("ID")) {
                    field.setAccessible(true);
                    statement.setObject(i++,field.get(t));
                }
            Field field = type.getDeclaredFields()[0];//set id
            field.setAccessible(true);
            statement.setObject(i, field.get(t));
            System.out.println(statement.toString());
            statement.executeUpdate();
            return true;
            //return createObjects(resultSet).get(0);
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        //return null;
        return false;
    }
    /**
     * This method is also a generic one and is used to delete the given object from its specific
     * type of table(entity), using the private createDeleteQuery method
     * @param id
     * @return
     */
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        //return null;
        return false;
    }
}
