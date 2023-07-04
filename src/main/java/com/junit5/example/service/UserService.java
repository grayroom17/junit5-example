package com.junit5.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.junit5.example.dto.User;
import lombok.Data;

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

    /*public static UserService getInstance() {
        return INSTANCE;
    }*/

    public boolean add(User user) {
        return users.add(user);
    }

}
