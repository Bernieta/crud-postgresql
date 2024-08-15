package com.crudpostgresql.services;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.crudpostgresql.config.DBConnection;
import com.crudpostgresql.entities.UserEntity;

public class UserServices {

    public List<UserEntity> getAllUsers() throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        Connection connection = DBConnection.connection();
        Statement statement = connection.createStatement();
        final String query = "SELECT * FROM users";
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            int id = result.getInt("id");
            String names = result.getString("names");
            int age = result.getInt("age");
            String email = result.getString("email");
            users.add(new UserEntity(id, names, age, email));
        }
        result.close();
        statement.close();
        connection.close();
        return users;
    }

    public UserEntity getUserById(int id) throws SQLException {
        Connection connection = DBConnection.connection();
        final String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (!result.next())
            return null;
        int userId = result.getInt("id");
        String names = result.getString("names");
        int age = result.getInt("age");
        String email = result.getString("email");
        return new UserEntity(userId, names, age, email);
    }

    public UserEntity createUser(UserEntity newUser) throws SQLException {
        Connection connection = DBConnection.connection();
        final String query = "INSERT INTO users (names, age, email) VALUES (?, ?, ?) RETURNING *";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newUser.getNames());
        statement.setInt(2, newUser.getAge());
        statement.setString(3, newUser.getEmail());
        ResultSet result = statement.executeQuery();
        if (!result.next())
            return null;
        int userId = result.getInt("id");
        String names = result.getString("names");
        int age = result.getInt("age");
        String email = result.getString("email");
        return new UserEntity(userId, names, age, email);
    }

    public UserEntity updateUser(UserEntity user) throws SQLException {
        Connection connection = DBConnection.connection();
        final String query = "UPDATE users SET names = ?, age = ?, email = ? WHERE id = ? RETURNING *";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getNames());
        statement.setInt(2, user.getAge());
        statement.setString(3, user.getEmail());
        statement.setInt(4, user.getId());
        ResultSet result = statement.executeQuery();
        if (!result.next())
            return null;
        int id = result.getInt("id");
        String names = result.getString("names");
        int age = result.getInt("age");
        String email = result.getString("email");
        return new UserEntity(id, names, age, email);
    }

    public UserEntity deleteUser(int id) throws SQLException {
        Connection connection = DBConnection.connection();
        final String query = "DELETE FROM users WHERE id = ? RETURNING *";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (!result.next())
            return null;
        int userId = result.getInt("id");
        String names = result.getString("names");
        int age = result.getInt("age");
        String email = result.getString("email");
        return new UserEntity(userId, names, age, email);
    }
}
