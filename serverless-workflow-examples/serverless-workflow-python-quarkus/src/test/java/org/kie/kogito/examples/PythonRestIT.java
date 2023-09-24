package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@QuarkusIntegrationTest
public class PythonRestIT {

    @Test
    public void testPython() {
        JsonNode node = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{}").when()
                .post("/python_helloworld")
                .then()
                .statusCode(201)
                .extract().as(JsonNode.class);
        assertThat(node.get("workflowdata").get("result")).hasSize(3);
    }
}
