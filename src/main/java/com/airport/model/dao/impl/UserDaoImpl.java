package com.airport.model.dao.impl;

import com.airport.model.dao.DefaultDao;
import com.airport.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DefaultDao {

    @Override
    public void create(Object entity, Connection connection) throws SQLException {
        connection.setAutoCommit(false);

        User user = (User) entity;
        String query = "insert into user (id, name, phone, role_id) values (?, ?, ?, ?);";

        try(PreparedStatement pStatement = connection.prepareStatement(query)) {

            pStatement.setInt(1, user.getId());
            pStatement.setString(2, user.getName());
            pStatement.setString(3, user.getPhone());
            pStatement.setInt(4, user.getRoleId());

            pStatement.executeUpdate();
        }
        connection.setAutoCommit(true);
    }

    @Override
    public List<User> read(String tableName, Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();

        String query = "select * from " + tableName;

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int roleID = rs.getInt("role_id");

                users.add(new User(id, name, phone, roleID));
            }
        }
        return users;
    }

    @Override
    public List<User> update(int userID, String newPhone, Connection connection) {
        List<User> updateUsers = new ArrayList<>();

        String query = "update user set phone = ? where id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPhone);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();

            PreparedStatement allUsers = connection.prepareStatement("select * from user");
            ResultSet rs = allUsers.executeQuery();

            // Для проверки.
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int roleID = rs.getInt("role_id");

                updateUsers.add(new User(id, name, phone, roleID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updateUsers;
    }

    @Override
    public void delete(int id, Connection connection) {

        String query = "delete from user where id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println(read("user", connection));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
