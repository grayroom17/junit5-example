package com.junit5.example.extension;

import java.lang.reflect.Field;

import com.junit5.example.dao.UserDao;
import com.junit5.example.service.UserService;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class PostProcessingExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        System.out.println("Post processing extension");
        Field[] fields = testInstance.getClass().getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Getter.class)) {
                field.set(testInstance, new UserService(new UserDao()));
            }
        }
    }

}
