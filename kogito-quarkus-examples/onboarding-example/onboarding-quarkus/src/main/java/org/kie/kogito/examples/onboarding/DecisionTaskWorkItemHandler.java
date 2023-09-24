package org.kie.kogito.examples.onboarding;

import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.kie.kogito.addons.quarkus.k8s.workitems.QuarkusDiscoveredEndpointCaller;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

public class DecisionTaskWorkItemHandler implements KogitoWorkItemHandler {

    private QuarkusDiscoveredEndpointCaller endpointCaller;

    public DecisionTaskWorkItemHandler(QuarkusDiscoveredEndpointCaller endpointCaller) {
        this.endpointCaller = endpointCaller;
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        Map<String, Object> results = endpointCaller.discoverAndCall(workItem, System.getenv("NAMESPACE"), "Decision", HttpMethod.POST);
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
