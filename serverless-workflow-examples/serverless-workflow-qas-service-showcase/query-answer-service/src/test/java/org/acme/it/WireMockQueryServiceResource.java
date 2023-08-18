package org.acme.it;

import java.util.HashMap;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class WireMockQueryServiceResource implements QuarkusTestResourceLifecycleManager {

    public static final String QUERY_SERVICE_URL = "query-service-url";

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(8283);
        wireMockServer.start();
        configureFor(wireMockServer.port());
        stubFor(post("/query-service/sendQuery")
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody("{}")));
        Map<String, String> properties = new HashMap<>();
        properties.put(QUERY_SERVICE_URL, wireMockServer.baseUrl());
        return properties;
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
