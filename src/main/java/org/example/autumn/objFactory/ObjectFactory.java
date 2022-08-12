package org.example.autumn.objFactory;

public interface ObjectFactory {

    <T> T createObject(Class<T> objClass);

}
