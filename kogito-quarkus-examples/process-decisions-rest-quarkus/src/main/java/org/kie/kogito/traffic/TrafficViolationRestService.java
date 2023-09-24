package org.kie.kogito.traffic;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class TrafficViolationRestService {

    @Inject
    @RestClient
    TrafficViolationRestClient client;

    public TrafficViolationResponse evaluate(Driver driver, Violation violation) {
        return client.post(toMap(driver, violation));
    }

    public Map<String, Object> toMap(Driver driver, Violation violation) {
        Map<String, Object> params = new HashMap<>();
        params.put("Violation", violation);
        params.put("Driver", driver);
        return params;
    }
}
