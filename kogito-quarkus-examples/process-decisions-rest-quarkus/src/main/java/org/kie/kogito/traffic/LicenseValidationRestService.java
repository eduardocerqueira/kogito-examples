package org.kie.kogito.traffic;

import java.util.Collections;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class LicenseValidationRestService {

    @Inject
    @RestClient
    LicenseValidationRestClient client;

    public Driver evaluate(Driver driver) {
        return client.post(Collections.singletonMap("driver", driver));
    }
}
