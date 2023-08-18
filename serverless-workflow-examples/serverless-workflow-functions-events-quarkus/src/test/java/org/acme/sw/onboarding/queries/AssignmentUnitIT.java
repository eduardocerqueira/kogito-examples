package org.acme.sw.onboarding.queries;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@QuarkusIntegrationTest
class AssignmentUnitIT {

    @Test
    void verifyNeurologyAssignmentCollection() {
        given()
                .body("{ \"patients\": [{ \"name\": \"Mick\", \"dateOfBirth\": \"1983-08-15\", \"symptoms\":[\"seizures\"]}] }")
                .contentType(ContentType.JSON)
                .when()
                .post("/assign-doctor")
                .then()
                .statusCode(200)
                .body("assignedDoctor.specialty", hasItem("Neurology"));
    }

    @Test
    void verifyNeurologyAssignment() {
        given()
                .body("{ \"patients\": [{ \"name\": \"Mick\", \"dateOfBirth\": \"1983-08-15\", \"symptoms\":[\"seizures\"]}] }")
                .contentType(ContentType.JSON)
                .when()
                .post("/assign-doctor/first")
                .then()
                .statusCode(200)
                .body("assignedDoctor.specialty", equalTo("Neurology"));
    }
}
