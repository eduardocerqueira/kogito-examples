package org.acme.numbers.serverless.workflow.functions;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

@QuarkusIntegrationTest
class RestExampleIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testRestExample() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("inputNumbers", new int[]{1, 2, 3, 4, 5, 6, 7}))
                .post("/RestExample")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("workflowdata", not(emptyOrNullString()));
    }
}
