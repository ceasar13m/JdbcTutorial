package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bl.Util;
import dao.EmployeeDAO;
import entity.Employee;

public class EmployeeService extends Util implements EmployeeDAO {
    private final String ADD = "INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, BIRTH_DAY, ADDRESS_ID) VALUES(?, ?, ?, ?, ?)";
    private final String GET_ALL = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DAY, ADDRESS_ID FROM EMPLOYEE";
    private final String GET_BY_ID = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DAY, ADDRESS_ID FROM EMPLOYEE WHERE ID=?";
    private final String UPDATE = "UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, BIRTH_DAY=?, ADDRESS_ID=? WHERE ID=?";
    private final String REMOVE = "DELETE FROM EMPLOYEE WHERE ID=?";

    Connection connection = getConnection();


    public void add(Employee employee) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(ADD);
            statement.setLong(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setDate(4, employee.getBirthDay());
            statement.setLong(5, employee.getAddressId());

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

    public List<Employee> getAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<Employee>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setBirthDay(resultSet.getDate("BIRTH_DAY"));
                employee.setAddressId(resultSet.getLong("ADDRESS_ID"));

                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return employeeList;
    }

    public Employee getById(Long id) throws SQLException {
        PreparedStatement statement = null;

        Employee employee = new Employee();
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            employee.setId(resultSet.getLong("ID"));
            employee.setFirstName(resultSet.getString("FIRST_NAME"));
            employee.setLastName(resultSet.getString("LAST_NAME"));
            employee.setBirthDay(resultSet.getDate("BIRTH_DAY"));
            employee.setAddressId(resultSet.getLong("ADDRESS_ID"));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return employee;
    }

    public void update(Employee employee) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setDate(3, employee.getBirthDay());
            statement.setLong(4, employee.getAddressId());
            statement.setLong(5, employee.getId());

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

    public void remove(Employee employee) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(REMOVE);

            statement.setLong(1, employee.getId());

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
