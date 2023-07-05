package com.junit5.example;

import com.junit5.example.extension.GlobalExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({
        GlobalExtension.class
})
public abstract class BaseTest {
}
