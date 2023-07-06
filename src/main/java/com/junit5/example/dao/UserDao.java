package com.junit5.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public boolean delete(Integer userId) {
        try (Connection connection = DriverManager.getConnection("url", "login", "password");
             PreparedStatement preparedStatement = connection.prepareStatement("delete FROM user where id = ?"))
        {
            preparedStatement.setObject(1, userId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
