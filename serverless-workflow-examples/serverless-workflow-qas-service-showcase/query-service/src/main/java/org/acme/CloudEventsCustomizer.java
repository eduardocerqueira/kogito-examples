package org.acme;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.jackson.JsonFormat;
import io.quarkus.jackson.ObjectMapperCustomizer;

/**
 * Ensure the registration of the CloudEvent jackson module according to the Quarkus suggested procedure.
 */
@Singleton
public class CloudEventsCustomizer implements ObjectMapperCustomizer {

    public void customize(ObjectMapper mapper) {
        mapper.registerModule(JsonFormat.getCloudEventJacksonModule());
    }
}
