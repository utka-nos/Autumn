package org.example.autumn.config;

import org.example.autumn.ObjectSearcher;
import org.reflections.Reflections;

import java.util.Set;

public interface ContextConfig {

    Set<ObjectSearcher> getSearchers();
}
