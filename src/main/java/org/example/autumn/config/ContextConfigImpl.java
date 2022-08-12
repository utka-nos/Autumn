package org.example.autumn.config;

import org.example.autumn.ObjectSearcher;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ContextConfigImpl implements ContextConfig {

    private Set<ObjectSearcher> objectSearchers = new HashSet<>();

    public ContextConfigImpl(ObjectSearcher ... objectSearchers) {
        this.objectSearchers = Arrays.stream(objectSearchers).collect(Collectors.toSet());

    }

    @Override
    public Set<ObjectSearcher> getSearchers() {
        return objectSearchers;
    }
}
