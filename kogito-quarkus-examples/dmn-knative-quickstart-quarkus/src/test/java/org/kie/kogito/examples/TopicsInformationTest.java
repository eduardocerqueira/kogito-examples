package org.kie.kogito.examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.ChannelType;
import org.kie.kogito.event.EventKind;
import org.kie.kogito.event.Topic;
import org.kie.kogito.event.cloudevents.CloudEventMeta;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class TopicsInformationTest {

    @Test
    void verifyTopicsInformation() {
        Topic expectedIncomingTopic = new Topic("kogito_incoming_stream", ChannelType.INCOMING);
        expectedIncomingTopic.setEventsMeta(Collections.singletonList(new CloudEventMeta("DecisionRequest", "", EventKind.CONSUMED)));

        Set<CloudEventMeta> expectedOutgoingEventMeta = new HashSet<>();
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponse", "Traffic+Violation", EventKind.PRODUCED));
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponse", "Traffic+Violation/FineService", EventKind.PRODUCED));
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponseFull", "Traffic+Violation", EventKind.PRODUCED));
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponseFull", "Traffic+Violation/FineService", EventKind.PRODUCED));
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponseError", "Traffic+Violation", EventKind.PRODUCED));
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponseError", "Traffic+Violation/FineService", EventKind.PRODUCED));
        expectedOutgoingEventMeta.add(new CloudEventMeta("DecisionResponseError", "__UNKNOWN_SOURCE__", EventKind.PRODUCED));
        Topic expectedOutgoingTopic = new Topic("kogito_outgoing_stream", ChannelType.OUTGOING);
        expectedOutgoingTopic.setEventsMeta(expectedOutgoingEventMeta);

        List<Topic> topics = Arrays.asList(given().get("/messaging/topics").as(Topic[].class));
        assertThat(topics, notNullValue());
        assertThat(topics, hasItem(expectedIncomingTopic));
        assertThat(topics, hasItem(expectedOutgoingTopic));
    }
}
