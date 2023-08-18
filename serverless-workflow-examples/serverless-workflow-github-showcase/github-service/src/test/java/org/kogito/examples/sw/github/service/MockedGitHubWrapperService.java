package org.kogito.examples.sw.github.service;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped
public class MockedGitHubWrapperService extends GitHubWrapperServiceImpl {

    @Override
    public void addLabels(String user, String repository, int issueId, List<String> labels) {

    }

    @Override
    public void addReviewers(String user, String repository, int prId, List<String> reviewers) {

    }

    @Override
    public List<String> fetchChangedFilesPath(String user, String repository, int prId) {
        return Collections.singletonList("myfile");
    }
}
