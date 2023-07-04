package com.junit5.example.service;

import java.util.*;

import com.junit5.example.dto.User;
import lombok.Data;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

//    private static final UserService INSTANCE = new UserService();

    private final List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public Optional<User> login(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Map<Integer, User> getMapOfAllUsers() {
        return users.stream()
                .collect(toMap(User::getId, identity()));
    }

    /*public static UserService getInstance() {
        return INSTANCE;
    }*/

    public boolean add(User user) {
        return users.add(user);
    }

    public boolean add(User... arrayOfUsers) {
        return users.addAll(Arrays.asList(arrayOfUsers));
    }
}
