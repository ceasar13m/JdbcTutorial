package dao;

import java.sql.SQLException;
import java.util.List;
import entity.Address;

public interface AddressDAO {

    //create
    void add(Address address) throws SQLException;

    //reade
    List<Address> getAll() throws SQLException;

    Address getById(Long id) throws SQLException;

    //update
    void update(Address address) throws SQLException;

    //delete
    void remove(Address address) throws SQLException;

}
