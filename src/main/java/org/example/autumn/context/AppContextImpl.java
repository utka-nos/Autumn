package org.example.autumn.context;

import lombok.SneakyThrows;
import org.example.autumn.annotations.obj.Obj;
import org.example.autumn.config.ContextConfig;
import org.example.autumn.objFactory.ObjectFactory;
import org.example.autumn.objFactory.ObjectFactoryImpl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AppContextImpl implements AppContext {

    private Map<Class<?>, Object> objectStorage = new ConcurrentHashMap<>();
    private ContextConfig ctxConf;
    private ObjectFactory objectFactory;

    public AppContextImpl(ContextConfig ctxConf) {
        this.ctxConf = ctxConf;
        this.objectFactory = new ObjectFactoryImpl(this);
    }


    @SneakyThrows
    @Override
    public <T> T getObject(Class<T> type) {
        if (objectStorage.containsKey(type)) {
            return (T) objectStorage.get(type);
        }

        Class<?> implClass = type;
        if (type.isInterface()) {
            implClass = getImplClass(type);
        }

        if (!implClass.isAnnotationPresent(Obj.class))
            throw new RuntimeException(type.toString() + " has no implementation with @Obj");

        T t = (T) objectFactory.createObject(implClass);

        objectStorage.put(type, t);

        return t;
    }

    private <T> Class<?> getImplClass(Class<T> type) {
        Set<Class<?>> subTypesOf = getSubTypesOfFromObjects(type);
        if (subTypesOf.size() != 1)
            throw new RuntimeException(type.toString() + " has 0 or more than 1 impls! Please, update your config");
        Class<?> implClass = subTypesOf.iterator().next();
        if (!implClass.isAnnotationPresent(Obj.class))
            throw new RuntimeException(type.toString() + " has no implementations with annotation @Obj");
        return implClass;
    }

    private <T> Set<Class<?>> getSubTypesOfFromObjects(Class<T> type) {
        Set<Class<?>> impls = objectStorage.keySet().stream().filter(type::isInstance).collect(Collectors.toSet());
        if (impls.size() == 0) {
            /*Set<Class<? extends T>> subTypesOf = ctxConf.getScanner().getSubTypesOf(type);
            for (Class<? extends T> c : subTypesOf) {
                if (c.isAnnotationPresent(Obj.class)) {
                    impls.add(c);
                }
            }*/
        }
        return impls;
    }

    @Override
    public ContextConfig getContextConfig() {
        return ctxConf;
    }
}
