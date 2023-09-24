package org.kie.kogito.serverless;

import java.util.Collections;
import java.util.Map;

import org.kie.kogito.event.cloudevents.CloudEventExtensionConstants;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class OperationsMockService implements QuarkusTestResourceLifecycleManager {

    private WireMockServer subtractionService;
    private WireMockServer multiplicationService;

    @Override
    public Map<String, String> start() {
        multiplicationService =
                this.startServer(8282,
                        "{  \"product\": 37.808 }");
        subtractionService =
                this.startServer(8181,
                        "{ \"difference\": 68.0 }");
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        if (subtractionService != null) {
            subtractionService.stop();
        }
        if (multiplicationService != null) {
            multiplicationService.stop();
        }
    }

    private WireMockServer startServer(final int port, final String response) {
        final WireMockServer server = new WireMockServer(port);
        server.start();
        server.stubFor(post(urlEqualTo("/"))
                .withHeader(CloudEventExtensionConstants.PROCESS_ID, WireMock.matching(".*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        return server;
    }
}
