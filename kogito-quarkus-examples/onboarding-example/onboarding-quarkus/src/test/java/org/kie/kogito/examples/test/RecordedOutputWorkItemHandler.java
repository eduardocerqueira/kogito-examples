package org.kie.kogito.examples.test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

public class RecordedOutputWorkItemHandler implements KogitoWorkItemHandler {

    private Map<String, Function<KogitoWorkItem, Map<String, Object>>> recorded = new HashMap<>();

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        Map<String, Object> results = recorded.remove(workItem.getParameter("TaskName")).apply(workItem);

        manager.completeWorkItem(workItem.getStringId(), results);
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {

    }

    public void record(String name, Function<KogitoWorkItem, Map<String, Object>> item) {
        this.recorded.put(name, item);
    }
}
