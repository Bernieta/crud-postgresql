package com.crudpostgresql.controllers;

import com.crudpostgresql.services.UserServices;
import com.crudpostgresql.entities.UserEntity;

import java.util.List;

import java.sql.SQLException;

public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    public List<UserEntity> getAllUsers() {
        try {
            List<UserEntity> users = userServices.getAllUsers();
            if (users.isEmpty())
                return null;
            return users;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public UserEntity getUserById(int id) {
        try {
            return userServices.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public UserEntity createUser(UserEntity newUser) {
        try {
            return userServices.createUser(newUser);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public UserEntity updateUser(UserEntity user) {
        try {
            return userServices.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public UserEntity deleteUser(int id) {
        try {
            return userServices.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
}
