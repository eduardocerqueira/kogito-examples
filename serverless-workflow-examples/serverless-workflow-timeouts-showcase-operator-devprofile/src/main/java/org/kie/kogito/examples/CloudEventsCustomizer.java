package org.kie.kogito.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.jackson.JsonFormat;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;

/**
 * Ensure the registration of the CloudEvent jackson module according to the Quarkus suggested procedure.
 */
@Singleton
public class CloudEventsCustomizer implements ObjectMapperCustomizer {

    public void customize(ObjectMapper mapper) {
        mapper.registerModule(JsonFormat.getCloudEventJacksonModule());
    }
}
