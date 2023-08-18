package org.kie.kogito.examples.onboarding;

import java.util.Map;

import org.kie.kogito.addons.springboot.k8s.workitems.SpringDiscoveredEndpointCaller;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.springframework.http.HttpMethod;

public class DecisionTaskWorkItemHandler implements KogitoWorkItemHandler {

    private SpringDiscoveredEndpointCaller endpointCaller;

    public DecisionTaskWorkItemHandler(SpringDiscoveredEndpointCaller endpointCaller) {
        this.endpointCaller = endpointCaller;
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        Map<String, Object> results = this.endpointCaller.discoverAndCall(workItem, System.getenv("NAMESPACE"), "Decision", HttpMethod.POST.toString());

        manager.completeWorkItem(workItem.getStringId(), results);
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {

    }

    @Override
    public String getName() {
        return "DecisionTask";
    }
}
