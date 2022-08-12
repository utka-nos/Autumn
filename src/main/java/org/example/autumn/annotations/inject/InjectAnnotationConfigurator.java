package org.example.autumn.annotations.inject;

import lombok.SneakyThrows;
import org.example.autumn.ObjectConfigurator;
import org.example.autumn.annotations.inject.Inject;
import org.example.autumn.context.AppContext;

import java.lang.reflect.Field;

public class InjectAnnotationConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object objectToConfigure, AppContext appContext) {
        Field[] declaredFields = objectToConfigure.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> type = field.getType();
                Object objToInject = appContext.getObject(type);
                field.setAccessible(true);
                field.set(objectToConfigure, objToInject);
            }
        }
    }
}
