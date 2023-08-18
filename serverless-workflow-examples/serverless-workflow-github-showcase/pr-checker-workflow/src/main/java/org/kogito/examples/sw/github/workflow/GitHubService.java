package org.kogito.examples.sw.github.workflow;

import java.util.Collections;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Simple wrapper class to call the github-service.
 */
@ApplicationScoped
public class GitHubService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubService.class);

    @Inject
    @RestClient
    GitHubClient gitHubClient;

    public JsonNode addLabels(JsonNode pullRequest) {
        LOGGER.info("Adding labels to PR");
        final String repoName = getRepoFullName(pullRequest);
        if (repoName == null) {
            return pullRequest;
        }
        if (pullRequest.get("labels") == null) {
            LOGGER.error("Skipping adding labels. 'labels' attribute not found in the PR object: {}", pullRequest);
            return pullRequest;
        }
        LOGGER.info("Calling GitHub Client to perform addLabels action");
        gitHubClient.addLabels(
                repoName.split("/")[0],
                repoName.split("/")[1],
                Objects.requireNonNull(pullRequest.get("number")).asInt(),
                Collections.singletonList(pullRequest.get("labels").asText()));
        return pullRequest;
    }

    public JsonNode addReviewers(JsonNode pullRequest) {
        LOGGER.info("Adding reviewers to PR");
        final String repoName = getRepoFullName(pullRequest);
        if (repoName == null) {
            return pullRequest;
        }
        if (pullRequest.get("reviewers") == null) {
            LOGGER.error("Skipping adding reviewers. 'reviewers' attribute not found in the PR object: {}", pullRequest);
            return pullRequest;
        }
        LOGGER.info("Calling GitHub Client to perform addReviewers action");
        gitHubClient.addReviewers(
                repoName.split("/")[0],
                repoName.split("/")[1],
                Objects.requireNonNull(pullRequest.get("number")).asInt(),
                Collections.singletonList(pullRequest.get("reviewers").asText()));
        return pullRequest;
    }

    public JsonNode fetchPRFiles(JsonNode pullRequest) {
        LOGGER.info("Fetching files for PR");
        final String repoName = getRepoFullName(pullRequest);
        if (repoName == null) {
            return pullRequest;
        }
        final JsonNode jsonNode = gitHubClient.fetchFiles(
                repoName.split("/")[0],
                repoName.split("/")[1],
                Objects.requireNonNull(pullRequest.get("number")).asInt());
        if (pullRequest.isObject()) {
            ((ObjectNode) pullRequest).replace("files", jsonNode);
        } else {
            LOGGER.error("Pull Request JsonNode is not an object: {}", pullRequest);
        }
        return pullRequest;
    }

    private String getRepoFullName(JsonNode pullRequest) {
        if (pullRequest.get("repository") == null) {
            LOGGER.error("Impossible to resolve the repository name for {}, no 'repository' tag found.", pullRequest);
            return null;
        }
        final String repoName = pullRequest.get("repository").get("full_name").asText();
        if ("".equals(repoName)) {
            LOGGER.error("Impossible to resolve the repository name for {}", pullRequest);
            return null;
        } else if (!repoName.contains("/")) {
            LOGGER.error("Wrong format for repository name {}", repoName);
            return null;
        }
        LOGGER.info("Extracted repository name from PR: {}", repoName);
        return repoName;
    }
}
