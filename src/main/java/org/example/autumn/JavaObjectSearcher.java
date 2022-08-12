package org.example.autumn;

import org.example.autumn.annotations.obj.Obj;
import org.example.autumn.context.ObjectDefinition;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

public class JavaObjectSearcher implements ObjectSearcher{

    private Reflections scanner;

    public JavaObjectSearcher(String pkgToScan) {
        scanner = new Reflections(pkgToScan);
    }

    @Override
    public Set<ObjectDefinition> searchObjects() {
        Set<ObjectDefinition> objectDefinitions = new HashSet<>();

        Set<Class<?>> typesAnnotatedWith = scanner.getTypesAnnotatedWith(Obj.class);

        for (Class<?> objClass : typesAnnotatedWith) {
            ObjectDefinition objectDefinition = new ObjectDefinition(objClass);
            objectDefinition.setObjectId(objClass.getName());
            objectDefinitions.add(objectDefinition);
        }

        return objectDefinitions;
    }
}
