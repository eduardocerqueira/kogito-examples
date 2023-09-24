package org.kie.kogito.performance.client;

import java.util.function.Consumer;

public interface RequestDispatcher extends AutoCloseable {
    void dispatch(long delay, Consumer<Throwable> callback);
}
