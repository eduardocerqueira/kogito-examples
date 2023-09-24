package org.acme.sw.onboarding.resources;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@QuarkusIntegrationTest
class PatientResourceIT {

    @Test
    void verifyStoreNewPatient() {
        given()
                .body("{ \"name\": \"Mick\", \"dateOfBirth\": \"1983-08-15\", \"symptoms\":[\"seizures\"]}")
                .contentType(ContentType.JSON)
                .when()
                .post("/onboarding/patient")
                .then()
                .statusCode(200)
                .body("id", not(empty()));
    }
}
