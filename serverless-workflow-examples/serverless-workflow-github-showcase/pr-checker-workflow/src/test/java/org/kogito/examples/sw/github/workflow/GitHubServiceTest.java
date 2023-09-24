package org.kogito.examples.sw.github.workflow;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@QuarkusTestResource(GitHubServiceMockServer.class)
class GitHubServiceTest {

    final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    GitHubService gitHubServiceBackend;

    @Test
    void addLabels() throws IOException {
        final JsonNode jsonNode = objectMapper.readTree(this.getClass().getResource("/mock/addLabels.json"));
        assertNotNull(jsonNode);
        final JsonNode reply = gitHubServiceBackend.addLabels(jsonNode);
        assertNotNull(reply);
        assertNotNull(reply.get("labels"));
    }

    @Test
    void addReviewers() throws IOException {
        final JsonNode jsonNode = objectMapper.readTree(this.getClass().getResource("/mock/addReviewers.json"));
        assertNotNull(jsonNode);
        final JsonNode reply = gitHubServiceBackend.addReviewers(jsonNode);
        assertNotNull(reply);
        assertNotNull(reply.get("reviewers"));
    }

    @Test
    void fetchPRFiles() throws IOException {
        final JsonNode jsonNode = objectMapper.readTree(this.getClass().getResource("/mock/addReviewers.json"));
        assertNotNull(jsonNode);
        final JsonNode reply = gitHubServiceBackend.fetchPRFiles(jsonNode);
        assertNotNull(reply);
        assertNotNull(reply.get("reviewers"));
        assertNotNull(reply.get("files"));
    }
}
