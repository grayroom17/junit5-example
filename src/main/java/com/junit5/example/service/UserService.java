package com.junit5.example.service;

import java.util.ArrayList;
import java.util.List;

import com.junit5.example.dto.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public boolean add(User user) {
        return users.add(user);
    }

}
