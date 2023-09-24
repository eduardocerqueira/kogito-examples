package org.acme;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

public class MockServices implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        configureWiremockServer();
        return Map.of(
                "quarkus.rest-client.stock_svc_yaml.url", wireMockServer.baseUrl(),
                "quarkus.rest-client.stock_portfolio_svc_yaml.url", wireMockServer.baseUrl()
        );
    }

    private void configureWiremockServer() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/stock-price/KGTO"))
                                       .willReturn(aResponse()
                                                           .withStatus(201)
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBody("{ \"symbol\": \"KGTO\",  \"currentPrice\": \"110\" }")));

        wireMockServer.stubFor(get(urlMatching("/profit/KGTO\\?currentPrice=.+"))
                                       .willReturn(aResponse()
                                                           .withStatus(201)
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBody("{ \"symbol\": \"KGTO\",  \"profit\": \"10%\" }")));
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
