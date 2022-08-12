package org.example.autumn.context;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public class ObjectDefinition {

    private Object object;
    private String objectId;
    private Class<?> objType;

    @SneakyThrows
    public ObjectDefinition(Class<?> type) {
        this.objType = type;
        this.objectId = type.getName();
        this.object = type.getDeclaredConstructor().newInstance();
    }
}
