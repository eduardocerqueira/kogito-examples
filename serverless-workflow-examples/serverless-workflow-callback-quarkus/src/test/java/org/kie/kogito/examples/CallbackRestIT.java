package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@QuarkusIntegrationTest
class CallbackRestIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testCallbackRest() {
        String id = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("/callback")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        await()
                .atLeast(1, SECONDS)
                .atMost(30, SECONDS)
                .with().pollInterval(1, SECONDS)
                .untilAsserted(() -> given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get("/callback/{id}", id)
                        .then()
                        .statusCode(404));
    }
}
