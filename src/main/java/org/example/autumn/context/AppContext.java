package org.example.autumn.context;

import org.example.autumn.config.ContextConfig;

public interface AppContext {

    <T> T getObject(Class<T> type);

    ContextConfig getContextConfig();

}
