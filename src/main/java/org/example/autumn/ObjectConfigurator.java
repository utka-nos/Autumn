package org.example.autumn;

import org.example.autumn.context.AppContext;

public interface ObjectConfigurator {
    void configure(Object objectToConfigure, AppContext appContext);
}
