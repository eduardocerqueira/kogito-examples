package org.acme.travel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        final Topic expectedIncomingTopic = new Topic("kogito_incoming_stream", ChannelType.INCOMING);
        expectedIncomingTopic.setEventsMeta(Collections.singletonList(new CloudEventMeta("travellers", "", EventKind.CONSUMED)));
        final Topic expectedOutgoingTopic = new Topic("kogito_outgoing_stream", ChannelType.OUTGOING);
        expectedOutgoingTopic.setEventsMeta(Collections.singletonList(new CloudEventMeta("process.travelers.processedtravellers", "/process/travelers", EventKind.PRODUCED)));

        final List<Topic> topics = Arrays.asList(given().get("/messaging/topics").as(Topic[].class));
        assertThat(topics, notNullValue());
        assertThat(topics, hasItem(expectedIncomingTopic));
        assertThat(topics, hasItem(expectedOutgoingTopic));
    }
}
