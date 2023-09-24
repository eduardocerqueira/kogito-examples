package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusIntegrationTest
public class OpenVinoRestIT {

    @Test
    public void testOpenvino() {
         given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"fileName\":\"data/coco.jpg\"}").when()
                .post("/openvino_helloworld")
                .then()
                .statusCode(201)
                .body("workflowdata.group", containsString("flat-coated retriever"));
       
    }
}
