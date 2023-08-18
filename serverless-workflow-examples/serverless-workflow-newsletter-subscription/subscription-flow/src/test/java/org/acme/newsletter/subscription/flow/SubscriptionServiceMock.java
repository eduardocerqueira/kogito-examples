package org.acme.newsletter.subscription.flow;

import java.util.Collections;
import java.util.Map;

import org.acme.newsletter.subscription.service.Subscription;
import org.acme.newsletter.subscription.service.SubscriptionResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.UrlPathPattern;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.acme.newsletter.subscription.flow.SubscriptionConstants.EMAIL;
import static org.acme.newsletter.subscription.flow.SubscriptionConstants.newSubscription;

public class SubscriptionServiceMock implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();

        ObjectMapper mapper = new ObjectMapper();

        try {
            wireMockServer.stubFor(post("/subscription")
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                            .withBody(mapper.writeValueAsString(newSubscription()))));

            final Subscription confirmedSub = newSubscription();
            confirmedSub.setVerified(true);
            wireMockServer.stubFor(put("/subscription/confirm")
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                            .withBody(mapper.writeValueAsString(confirmedSub))));
            wireMockServer.stubFor(get(new UrlPathPattern(new EqualToPattern("/subscription/verify"), true)).withQueryParam("email", new EqualToPattern(EMAIL))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                            .withBody(mapper.writeValueAsString(new SubscriptionResource.EmailVerificationReply(EMAIL, false)))));

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Impossible to convert Subscription to JSON", e);
        }

        return Collections.singletonMap("quarkus.rest-client.subscription_service_yaml.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
