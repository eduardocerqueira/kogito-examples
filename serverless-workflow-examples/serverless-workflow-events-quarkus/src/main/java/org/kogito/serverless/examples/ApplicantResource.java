package org.kogito.serverless.examples;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.avro.AvroCloudEventMarshaller;
import org.kie.kogito.event.avro.AvroIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import io.cloudevents.core.builder.CloudEventBuilder;

@Path("/newapplicant")
public class ApplicantResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicantResource.class);

    @Inject
    AvroIO avroIO;

    @Inject
    @Channel("out-applicants")
    Emitter<byte[]> newApplicantEmitter;

    private CloudEventMarshaller<byte[]> marshaller;

    @PostConstruct
    void init() {
        marshaller = new AvroCloudEventMarshaller(avroIO);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void submitApplicant(JsonNode newApplicant) {
        try {
            newApplicantEmitter.send(marshaller.marshall(CloudEventBuilder.v1()
                    .withId(UUID.randomUUID().toString())
                    .withType("applicants")
                    .withSource(URI.create("http://localhost:8080"))
                    .withData(marshaller.cloudEventDataFactory().apply(newApplicant))
                    .build()));
        } catch (IOException e) {
            LOGGER.error("Unable to process cloud event", e);
            throw new InternalServerErrorException("Unable to write cloud event data", e);
        }
    }
}
