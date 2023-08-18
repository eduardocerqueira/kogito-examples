package org.acme.sw.onboarding.resources;

import java.time.Duration;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@QuarkusIntegrationTest
class OnboardingIT {

    @Test
    void verifyOnboardingWorkflow() {
        Integer appointments = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/onboarding/schedule/appointment")
                .then()
                .statusCode(200)
                .extract().path("$.size()");

        given()
                .body("{ \"name\": \"Yoda\", \"dateOfBirth\": \"1963-08-15\", \"symptoms\":[\"seizures\"]}")
                .header("ce-specversion", "1.0")
                .header("ce-type", "new.patient.events")
                .header("ce-source", "/hospital/entry")
                .header("ce-id", UUID.randomUUID().toString())
                .contentType(ContentType.JSON)
                .when()
                .post("/")
                .then()
                .statusCode(202);

        await().atMost(Duration.ofMinutes(1)).untilAsserted(() -> given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/onboarding/schedule/appointment")
                .then()
                .statusCode(200)
                .body("$", hasSize(appointments + 1))
                .body(format("[%s].doctor.name", appointments), is("Maria Mind"))
                .body(format("[%s].patient.name", appointments), is("Yoda")));
    }
}
