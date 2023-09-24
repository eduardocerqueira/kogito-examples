package org.kie.kogito.examples;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.wiremock.webhooks.Webhooks.*;
import org.junit.jupiter.api.BeforeEach;
import org.wiremock.webhooks.Webhooks;

import java.util.Collections;
import java.util.UUID;

@QuarkusIntegrationTest
public class CallbackRestIT {

    private WireMockServer callbackServer;


    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @BeforeEach
    void setUp() {
        callbackServer = new WireMockServer(wireMockConfig().extensions(Webhooks.class).port(8181));
        callbackServer.start();
    }

    @Test
    void testCallbackRest() {
        mockWebServer();

        String id = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Collections.singletonMap("message", "Old data"))
                .post("/callback")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/callback/{id}", id)
                .then()
                .statusCode(404);

    }

    private void mockWebServer() {

        callbackServer.stubFor(post(urlEqualTo("/event"))
                .willReturn(aResponse().withStatus(200))
                .withPostServeAction("webhook", webhook()
                        .withMethod("POST")
                        .withUrl("http://localhost:" + 8081 + "/wait")
                        .withHeader("Content-Type", "application/json")
                        .withHeader("ce-specversion", "1.0")
                        .withHeader("ce-id",UUID.randomUUID().toString())
                        .withHeader("ce-source", "")
                        .withHeader("ce-type", "wait")
                        .withHeader("ce-kogitoprocrefid","{{jsonPath originalRequest.body '$.processInstanceId'}}")
                        .withBody("{ \"message\": \"New event\" }")));

    }
    @AfterEach
    void tearDown() {
        callbackServer.shutdownServer();
    }


}
