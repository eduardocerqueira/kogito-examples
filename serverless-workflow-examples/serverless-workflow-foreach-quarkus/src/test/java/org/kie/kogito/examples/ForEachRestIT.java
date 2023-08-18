package org.kie.kogito.examples;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusIntegrationTest
class ForEachRestIT {

    @Test
    void testForEachRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"input\" : [1,2,3]}").when()
                .post("/foreach")
                .then()
                .statusCode(201)
                .body("workflowdata.output", is(Arrays.asList(2, 3, 4)))
                .body("workflowdata.input", nullValue());
    }
}
