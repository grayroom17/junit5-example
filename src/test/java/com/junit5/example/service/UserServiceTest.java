package com.junit5.example.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.junit5.example.dto.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        //hamcrest
        MatcherAssert.assertThat(users, IsEmptyCollection.empty());
        //junit5
        assertTrue(users.isEmpty());
    }

    @Test
    void userSizeIfUsersAdded() {
        System.out.println("Test 2: " + this);
        userService.add(IVAN);
        userService.add(PETR);

        List<User> users = userService.getAll();

        //hamcrest
        MatcherAssert.assertThat(users, Matchers.hasSize(2));
        //assertJ
        assertThat(users).hasSize(2);
        //junit5
        assertEquals(2, users.size());
    }

    @Test
    void loginSuccessIfUserExists() {
        userService.add(IVAN);
        Optional<User> user = userService.login(IVAN.getLogin(), IVAN.getPassword());

        assertThat(user).isPresent();
        assertTrue(user.isPresent());

        user.ifPresent(u -> assertThat(u).isEqualTo(IVAN));
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

    @Test
    void usersConvertedToMapById() {
        userService.add(IVAN, PETR);

        Map<Integer, User> users = userService.getMapOfAllUsers();

        MatcherAssert.assertThat(users, IsMapContaining.hasKey(IVAN.getId()));
        assertAll(
                () -> assertThat(users).containsKeys(IVAN.getId(), PETR.getId()),
                () -> assertThat(users).containsValues(IVAN, PETR)
        );

    }

    @Test
    void throwExceptionIfUserPasswordIsNull() {
        assertAll(
                () -> {
                    IllegalArgumentException exception =
                            assertThrows(IllegalArgumentException.class, () -> userService.login(null, "password"));
                    assertThat(exception.getMessage()).isEqualTo("Username or password is null");
                },
                () -> {
                    IllegalArgumentException exception =
                            assertThrows(IllegalArgumentException.class, () -> userService.login("login", null));
                    assertThat(exception.getMessage()).isEqualTo("Username or password is null");
                }
        );
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