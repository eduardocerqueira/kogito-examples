package org.kie.kogito.examples;

import org.kie.kogito.examples.test.RecordedOutputWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;

public class WorkItemHandlerConfig extends BaseWorkItemHandlerConfig {

    private RecordedOutputWorkItemHandler handler = new RecordedOutputWorkItemHandler();

    @Override
    public KogitoWorkItemHandler forName(String name) {
        if (supportedHandlers.contains(name)) {
            return handler;
        }
        return super.forName(name);
    }
}
