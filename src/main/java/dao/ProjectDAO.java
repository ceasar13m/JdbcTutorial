package dao;

import java.sql.SQLException;
import java.util.List;
import entity.Project;

public interface ProjectDAO {

    //create
    void add(Project project) throws SQLException;

    //reade
    List<Project> getAll() throws SQLException;

    Project getById(Long id) throws SQLException;

    //update
    void update(Project project) throws SQLException;

    //delete
    void remove(Project project) throws SQLException;
}
