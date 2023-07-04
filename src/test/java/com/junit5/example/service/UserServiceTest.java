package com.junit5.example.service;

import java.util.List;
import java.util.Optional;

import com.junit5.example.dto.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    public static final User IVAN = User.of(1, "Ivan", "123");
    public static final User PETR = User.of(2, "Petr", "111");

    private UserService userService;

    @BeforeAll
    static void init() {
        System.out.println("Before all: ");
    }

    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
//        userService = UserService.getInstance();
        userService = new UserService();
    }

    @Test
    void usersEmptyIfNoUsersAdded() {
        System.out.println("Test 1: " + this);
        List<User> users = userService.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void userSizeIfUsersAdded() {
        System.out.println("Test 2: " + this);
        userService.add(IVAN);
        userService.add(PETR);

        List<User> users = userService.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void loginSuccessIfUserExists() {
        userService.add(IVAN);
        Optional<User> user = userService.login(IVAN.getLogin(), IVAN.getPassword());

        assertTrue(user.isPresent());
        user.ifPresent(u -> assertEquals(IVAN.getLogin(), u.getLogin()));
        user.ifPresent(u -> assertEquals(IVAN.getPassword(), u.getPassword()));
    }

    @Test
    void loginFailIfPasswordIsNotCorrect() {
        userService.add(IVAN);
        Optional<User> user = userService.login(IVAN.getLogin(), "incorrectPassword");

        assertTrue(user.isEmpty());
    }

    @Test
    void loginFailIfUserNotExists() {
        Optional<User> user = userService.login("notExistedUser", "password");

        assertTrue(user.isEmpty());
    }

    @AfterEach
    void deleteDataFromDataBase() {
        System.out.println("After each: " + this);
    }

    @AfterAll
    static void closeConnectionPool() {
        System.out.println("After all: ");
    }

}