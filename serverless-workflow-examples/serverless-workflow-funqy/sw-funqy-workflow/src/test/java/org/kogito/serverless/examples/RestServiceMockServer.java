package org.kogito.serverless.examples;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RestServiceMockServer implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, String> start() {

        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8082));
        wireMockServer.start();

        ObjectNode country = mapper.createObjectNode().put("name", "Brazil").put("capital", "Brasilia").put("region", "South America");
        wireMockServer.stubFor(post(urlEqualTo("/country"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withJsonBody(country)
                        .withHeader("Content-Type", "application/json")));
        wireMockServer.stubFor(post(urlEqualTo("/population"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withJsonBody(country.put("population", "211,000,000"))
                        .withHeader("Content-Type", "application/json")));
        wireMockServer.stubFor(post(urlEqualTo("/classify"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withJsonBody(country.put("classifier", "Large"))
                        .withHeader("Content-Type", "application/json")));

        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
