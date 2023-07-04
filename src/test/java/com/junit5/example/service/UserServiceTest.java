package com.junit5.example.service;

import java.util.List;

import com.junit5.example.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    @Test
    void usersEmptyIfNoUsersAdded() {
        UserService userService = UserService.getInstance();
        List<User> users = userService.getAll();
        assertTrue(users.isEmpty());
    }

}