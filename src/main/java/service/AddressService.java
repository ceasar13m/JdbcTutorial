package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bl.Util;
import dao.AddressDAO;
import entity.Address;

public class AddressService extends Util implements AddressDAO {

    private final String ADD = "INSERT INTO ADDRESS (ID, COUNTRY, CITY, STREET, POST_CODE) VALUES(?, ?, ?, ?, ?)";
    private final String GET_ALL = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS";
    private final String GET_BY_ID = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID=?";
    private final String UPDATE = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";
    private final String REMOVE = "DELETE FROM ADDRESS WHERE ID=?";

    private Connection connection = getConnection();

    public void add(Address address) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(ADD);
            statement.setLong(1, address.getId());
            statement.setString(2, address.getCountry());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getStreet());
            statement.setString(5, address.getPostCode());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<Address> getAll() throws SQLException {
        List<Address> addressList = new ArrayList<Address>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getLong("ID"));
                address.setCountry(resultSet.getString("COUNTRY"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setPostCode(resultSet.getString("POST_CODE"));

                addressList.add(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return addressList;
    }

    public Address getById(Long id) throws SQLException {
        PreparedStatement statement = null;

        Address address = new Address();
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            address.setId(resultSet.getLong("ID"));
            address.setCountry(resultSet.getString("COUNTRY"));
            address.setCity(resultSet.getString("CITY"));
            address.setStreet(resultSet.getString("STREET"));
            address.setStreet(resultSet.getString("POST_CODE"));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return address;
    }

    public void update(Address address) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE);

            statement.setString(1,address.getCountry());
            statement.setString(2,address.getCity());
            statement.setString(3,address.getStreet());
            statement.setString(4,address.getPostCode());
            statement.setLong(5,address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void remove(Address address) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(REMOVE);

            statement.setLong(1, address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
    }
}
