package com.junit5.example.service;

import java.util.Collections;
import java.util.List;

import com.junit5.example.dto.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();


    public List<User> getAll() {
        return Collections.emptyList();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
