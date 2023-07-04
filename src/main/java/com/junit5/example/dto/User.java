package com.junit5.example.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class User {
    Integer id;
    String login;
    String password;
}