package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bl.Util;
import dao.EmplProjDAO;
import entity.EmplProj;
import entity.Project;

public class EmplProjService extends Util implements EmplProjDAO {

    private final String ADD = "INSERT INTO EMPL_PROJ (EMPLOYEE_ID, PROJECT_ID) VALUES(?, ?)";
    private final String GET_ALL = "SELECT EMPLOYEE_ID, PROJECT_ID FROM EMPL_PROJ";
    private final String GET_BY_ID = "SELECT EMPLOYEE_ID, PROJECT_ID FROM EMPL_PROJ WHERE EMPLOYEE_ID=? AND PROJECT_ID=?";
    private final String UPDATE = "UPDATE EMPL_PROJ SET EMPLOYEE_ID=? AND PROJECT_ID=?";
    private final String REMOVE = "DELETE FROM EMPL_PROJ WHERE EMPLOYEE_ID=? AND PROJECT_ID=?";

    Connection connection = getConnection();

    public void add(EmplProj emplProj) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(ADD);
            statement.setLong(1, emplProj.getEmployeeId());
            statement.setLong(2, emplProj.getProjectId());

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

    public List<EmplProj> getAll() throws SQLException {
        List<EmplProj> emplProjList = new ArrayList<EmplProj>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                EmplProj emplProj = new EmplProj();
                emplProj.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
                emplProj.setProjectId(resultSet.getLong("PROJECT_ID"));

                emplProjList.add(emplProj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return emplProjList;
    }

    public EmplProj getByEmployeeIdAndProjectId(Long employeeId, Long projectId) throws SQLException {
        PreparedStatement statement = null;

        EmplProj emplProj = new EmplProj();
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, employeeId);
            statement.setLong(2, projectId);

            ResultSet resultSet = statement.executeQuery();
            emplProj.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
            emplProj.setProjectId(resultSet.getLong("PROJECT_ID"));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return emplProj;
    }

    public void update(EmplProj emplProj) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE);

            statement.setLong(1, emplProj.getEmployeeId());
            statement.setLong(2, emplProj.getProjectId());

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

    public void remove(EmplProj emplProj) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(REMOVE);

            statement.setLong(1, emplProj.getEmployeeId());
            statement.setLong(2, emplProj.getProjectId());

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
