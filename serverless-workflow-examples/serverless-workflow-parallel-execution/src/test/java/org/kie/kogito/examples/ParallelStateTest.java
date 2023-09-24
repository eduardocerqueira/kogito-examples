package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasLength;

@QuarkusTest
class ParallelStateTest {

    @Test
    void testPartialParallelRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{}").when()
                .post("/parallel")
                .then()
                .statusCode(201)
                .body("workflowdata.result", hasLength(2));
    }
}
