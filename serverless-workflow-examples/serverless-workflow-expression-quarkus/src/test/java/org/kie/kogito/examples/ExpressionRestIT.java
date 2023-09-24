package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusIntegrationTest
class ExpressionRestIT {

    @Test
    void testErrorRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"numbers\":[{\"x\":2, \"y\": 1},{\"x\":4, \"y\": 3}]}").when()
                .post("/expression")
                .then()
                .statusCode(201)
                .body("workflowdata.result", is(4))
                .body("workflowdata.number", nullValue());
    }
}
