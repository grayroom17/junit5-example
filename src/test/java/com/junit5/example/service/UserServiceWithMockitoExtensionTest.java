package com.junit5.example.service;

import com.junit5.example.BaseTest;
import com.junit5.example.dao.UserDao;
import com.junit5.example.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith({
        MockitoExtension.class
})
class UserServiceWithMockitoExtensionTest extends BaseTest {

    public static final User IVAN = User.of(1, "Ivan", "123");
    public static final User PETR = User.of(2, "Petr", "111");

    @InjectMocks
    private UserService userService;
    @Mock(strictness = Mock.Strictness.LENIENT)
    private UserDao userDao;

    @BeforeEach
    void preparation() {
        Mockito.doReturn(true).when(userDao).delete(IVAN.getId());
    }

    @Test
    void deleteWithMock() {
        userService.add(IVAN);
        boolean result = userService.delete(IVAN.getId());

        assertThat(result).isTrue();
    }

    @Test
    void throwExceptionIfDatabaseIsNotAvailable() {
        Integer userId = IVAN.getId();
        doThrow(RuntimeException.class).when(userDao).delete(userId);
        assertThrows(RuntimeException.class, () -> userService.delete(userId));
    }

}