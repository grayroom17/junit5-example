package com.junit5.example.service;

import com.junit5.example.dao.UserDao;
import com.junit5.example.dto.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class UserServiceWithMocksTest {

    public static final User IVAN = User.of(1, "Ivan", "123");
    public static final User PETR = User.of(2, "Petr", "111");

    @Test
    void deleteWithMock() {
        UserDao userDao = mock(UserDao.class);
        doReturn(true).when(userDao).delete(IVAN.getId());
//        when(userDao.delete(IVAN.getId())).thenReturn(true);
        UserService userService = new UserService(userDao);

        userService.add(IVAN);

        boolean result = userService.delete(IVAN.getId());

        assertThat(result).isTrue();
    }

    @Test
    void deleteWithSpy() {
        UserDao userDao = spy(new UserDao());
        doReturn(true).when(userDao).delete(IVAN.getId());
//        when(userDao.delete(IVAN.getId())).thenReturn(true);
        UserService userService = new UserService(userDao);

        userService.add(IVAN);

        boolean result = userService.delete(IVAN.getId());

        assertThat(result).isTrue();

        userService.delete(IVAN.getId());
        userService.delete(IVAN.getId());

        verify(userDao, Mockito.times(3)).delete(IVAN.getId());

    }

}
