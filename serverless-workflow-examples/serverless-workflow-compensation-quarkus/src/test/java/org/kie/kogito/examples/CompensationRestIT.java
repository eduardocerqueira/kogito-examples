package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
public class CompensationRestIT {

    @Test
    public void testErrorRest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"shouldCompensate\" : true}").when()
                .post("/compensation")
                .then()
                .statusCode(201)
                .body("workflowdata.compensated", is(true))
                .body("workflowdata.compensating_more", is("Real Betis Balompie"));
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"shouldCompensate\" : false}").when()
                .post("/compensation")
                .then()
                .statusCode(201)
                .body("workflowdata.compensated", is(false));
    }
}
