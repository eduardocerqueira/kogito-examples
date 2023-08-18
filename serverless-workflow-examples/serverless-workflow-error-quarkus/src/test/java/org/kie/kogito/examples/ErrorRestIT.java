package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
public class ErrorRestIT {

    @Test
    public void testErrorRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"number\" : 12345}").when()
                .post("/error")
                .then()
                .statusCode(201)
                .body("workflowdata.numberType", is("odd"));
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"number\" : 4}").when()
                .post("/error")
                .then()
                .statusCode(201)
                .body("workflowdata.numberType", is("odd"));
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"number\" : 2}").when()
                .post("/error")
                .then()
                .statusCode(201)
                .body("workflowdata.numberType", is("even"));
    }
}
