package org.kogito.examples.sw.github.workflow;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.internal.Files;

import io.cloudevents.jackson.JsonFormat;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@QuarkusTestResource(GitHubServiceMockServer.class) // mock the GitHub API
@QuarkusTestResource(MessageSinkServer.class) // mock the Knative Eventing Broker
class PRCheckerWorkflowTest {

    final static Logger LOGGER = LoggerFactory.getLogger(PRCheckerWorkflowTest.class);

    @Test
    void onPREdited() throws IOException {
        final String pullRequestEvent = Files.read(this.getClass().getResourceAsStream("/mock/ce_pr_edited.json"), Charset.defaultCharset());
        assertNotNull(pullRequestEvent);
        LOGGER.debug("CE read as {}", pullRequestEvent);

        given()
                .contentType(JsonFormat.CONTENT_TYPE)
                .body(pullRequestEvent).post("/").then().statusCode(202);
    }
}
