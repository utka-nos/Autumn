package org.example.autumn.objFactory;

import lombok.SneakyThrows;
import org.example.autumn.ObjectConfigurator;
import org.example.autumn.ObjectSearcher;
import org.example.autumn.context.AppContext;
import org.example.autumn.context.ObjectDefinition;

import java.util.HashSet;
import java.util.Set;

public class ObjectFactoryImpl implements ObjectFactory {

    private AppContext appContext;

    private Set<ObjectDefinition> objectDefinitionSet = new HashSet<>();

    private Set<ObjectConfigurator> objectConfigurators = new HashSet<>();

    private Set<ObjectSearcher> objectSearchers = new HashSet<>();

    @SneakyThrows
    public ObjectFactoryImpl(AppContext appContext) {
        this.appContext = appContext;

        objectSearchers = appContext.getContextConfig().getSearchers();

        objectDefinitionSet = getObjectDefinitionMap();

        //todo: search configurators from objDefSet



        /*Set<Class<? extends ObjectConfigurator>> configurators =
                appContext.getContextConfig().getScanner().getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> confClass : configurators) {
            objectConfigurators.add(confClass.getDeclaredConstructor().newInstance());
        }*/
    }

    private Set<ObjectDefinition> getObjectDefinitionMap() {
         Set<ObjectDefinition> objDefs = new HashSet<>();

        return null;
    }

    @SneakyThrows
    @Override
    public <T> T createObject(Class<T> type) {
        T t = type.getDeclaredConstructor().newInstance();

        configureObject(t);

        return t;
    }

    private <T> void configureObject(T t) {
        objectConfigurators.forEach(conf -> conf.configure(t, appContext));
    }
}
