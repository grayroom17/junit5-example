package com.junit5.example.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class GlobalExtension implements BeforeAllCallback, AfterTestExecutionCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        System.out.println("Before all callback");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        System.out.println("After test execution callback");
    }

}
