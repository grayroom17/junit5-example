package com.junit5.example.service;

import com.junit5.example.dao.UserDao;
import com.junit5.example.dto.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceWithMocksTest {

    public static final User IVAN = User.of(1, "Ivan", "123");
    public static final User PETR = User.of(2, "Petr", "111");

    @Test
    void delete() {
        UserDao userDao = mock(UserDao.class);
        doReturn(true).when(userDao).delete(IVAN.getId());
//        when(userDao.delete(IVAN.getId())).thenReturn(true);
        UserService userService = new UserService(userDao);

        userService.add(IVAN);

        boolean result = userService.delete(IVAN.getId());

        Assertions.assertThat(result).isTrue();
    }
}
