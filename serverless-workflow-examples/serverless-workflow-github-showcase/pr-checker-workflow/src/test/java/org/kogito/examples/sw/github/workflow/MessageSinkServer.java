package org.kogito.examples.sw.github.workflow;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;

/**
 * Mocked server to receive the produced messages by our SW.
 */
@ApplicationScoped
public class MessageSinkServer implements QuarkusTestResourceLifecycleManager {

    WireMockServer sinkServer;

    @Produces
    public WireMockServer getSinkServer() {
        return sinkServer;
    }

    @Override
    public Map<String, String> start() {
        sinkServer = new WireMockServer(WireMockConfiguration.options().port(8181));
        sinkServer.start();
        sinkServer.stubFor(post("/").willReturn(aResponse().withBody("ok").withStatus(200)));

        return null;
    }

    @Override
    public void stop() {
        if (sinkServer != null) {
            sinkServer.stop();
        }
    }
}
