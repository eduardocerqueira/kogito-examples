package org.kie.kogito.examples;

import io.quarkus.funqy.Funq;
import io.quarkus.funqy.knative.events.CloudEvent;
import io.quarkus.funqy.knative.events.CloudEventBuilder;

public class CloudEventFunction {

    @Funq
    public CloudEvent<Void> cloudEventFunction(CloudEvent<Void> cloudEvent) {
        return CloudEventBuilder.create()
                .id("response-of-" + cloudEvent.id())
                .build();
    }

}
