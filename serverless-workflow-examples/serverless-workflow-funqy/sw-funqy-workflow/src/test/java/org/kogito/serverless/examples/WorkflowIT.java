package org.kogito.serverless.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
@QuarkusTestResource(RestServiceMockServer.class)
public class WorkflowIT {

    @Test
    public void testWorkflow() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\" : \"Brazil\"}").when()
                .when().post("/countryworkflow")
                .then()
                .statusCode(201);

    }

}
