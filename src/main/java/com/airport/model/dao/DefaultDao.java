package com.airport.model.dao;

import com.airport.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DefaultDao<T> {
    // CRUD
    void create(T entity, Connection connection) throws SQLException;
    List<T> read(String tableName, Connection connection) throws SQLException;

    List<T> update(int id, String phone, Connection connection);

    void delete(int id, Connection connection);
}
