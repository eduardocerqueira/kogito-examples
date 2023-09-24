package org.kogito.examples.sw.notification.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

// Those are simple tests to verify if the integration is running.
// Edit the application.properties file with the right credentials, disabled this test and run.
// Check yr slack channel
@Disabled
@QuarkusTest
class NotificationResourceIT {

    @Test
    void simpleCheckSendSlackMessage() {
        given().when()
                .body("Hello World from silly integration test")
                .post("/plain")
                .then()
                .statusCode(200);
    }

    @Test
    void simpleCheckSendSlackMessageCloudEvent() {
        given().config(RestAssured.config().encoderConfig(RestAssured.config().getEncoderConfig().encodeContentTypeAs("application/cloudevents", ContentType.TEXT)))
                .when()
                .body("{ \"number\": 1000, \"pull_request\": { \"title\": \"Hello from cloud events! :cloud:\" } }")
                .header("ce-specversion", "1.0")
                .header("ce-id", "000")
                .header("ce-type", "notification")
                .header("ce-source", "http://github.com")
                .contentType("application/cloudevents")
                .post("/")
                .then()
                .statusCode(200);
    }
}
