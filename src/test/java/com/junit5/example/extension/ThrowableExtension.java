package com.junit5.example.extension;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ThrowableExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof IOException || throwable.getCause() instanceof SQLException) {
            throw throwable;
        }
    }

}
