package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bl.Util;
import dao.ProjectDAO;
import entity.Project;

public class ProjectService extends Util implements ProjectDAO {

    private final String ADD = "INSERT INTO PROJECT (ID, TITLE) VALUES(?, ?)";
    private final String GET_ALL = "SELECT ID, TITLE FROM PROJECT";
    private final String GET_BY_ID = "SELECT ID, TITLE FROM PROJECT WHERE ID=?";
    private final String UPDATE = "UPDATE PROJECT SET TITLE=? WHERE ID=?";
    private final String REMOVE = "DELETE FROM PROJECT WHERE ID=?";

    Connection connection = getConnection();

    public void add(Project project) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(ADD);
            statement.setLong(1, project.getId());
            statement.setString(2, project.getTitle());

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

    public List<Project> getAll() throws SQLException {
        List<Project> projectList = new ArrayList<Project>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getLong("ID"));
                project.setTitle(resultSet.getString("TITLE"));

                projectList.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return projectList;
    }

    public Project getById(Long id) throws SQLException {
        PreparedStatement statement = null;

        Project project = new Project();
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            project.setId(resultSet.getLong("ID"));
            project.setTitle(resultSet.getString("TITLE"));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return project;
    }

    public void update(Project project) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE);

            statement.setString(1, project.getTitle());
            statement.setLong(2, project.getId());

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

    public void remove(Project project) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(REMOVE);

            statement.setLong(1, project.getId());

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
