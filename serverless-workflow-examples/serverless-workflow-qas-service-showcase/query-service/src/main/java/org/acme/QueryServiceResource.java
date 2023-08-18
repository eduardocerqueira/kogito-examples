package org.acme;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.jackson.JsonCloudEventData;
import io.quarkus.reactivemessaging.http.runtime.OutgoingHttpMetadata;

@Path("query-service")
@ApplicationScoped
public class QueryServiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryServiceResource.class);

    private static final String RESPONSE_EVENTS = "response_events";

    @Inject
    QueryRequestRepository repository;

    @Inject
    @Channel(RESPONSE_EVENTS)
    Emitter<String> eventsEmitter;

    @Inject
    ObjectMapper objectMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<QueryRequest> get() {
        return repository.getAll();
    }

    @Path("sendQuery")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendQuery(QueryRequest request) {
        LOGGER.debug("Query request received: {}", request);
        repository.saveOrUpdate(request);
        return Response.ok("{}").build();
    }

    @Path("resolveQuery")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resolveQuery(ResolveRequest request) {
        String event = generateCloudEvent(request.getProcessInstanceId(), request.getQueryResponse());
        LOGGER.debug("Resolving query for processInstanceId:{}, event to send is: {}", request.getProcessInstanceId(), event);
        eventsEmitter.send(Message.of(event).addMetadata(new OutgoingHttpMetadata.Builder().addHeader("content-type", "application/cloudevents+json").build()));
        repository.delete(request.getProcessInstanceId());
        return Response.ok("{}").build();
    }

    public String generateCloudEvent(String processInstanceId, String queryResponse) {
        try {
            return objectMapper.writeValueAsString(CloudEventBuilder.v1()
                    .withId(UUID.randomUUID().toString())
                    .withSource(URI.create("query-service"))
                    .withType("query_response_events")
                    .withTime(OffsetDateTime.now())
                    .withExtension("kogitoprocrefid", processInstanceId)
                    .withData(JsonCloudEventData.wrap(objectMapper.createObjectNode().put("answer", queryResponse)))
                    .build());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}