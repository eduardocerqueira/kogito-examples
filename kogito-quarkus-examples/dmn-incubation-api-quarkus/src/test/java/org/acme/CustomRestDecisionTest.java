package org.acme;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CustomRestDecisionTest {

    String body = "{" +
            "    \"Driver\": {" +
            "        \"Points\" : 2" +
            "    }," +
            "    \"Violation\": {" +
            "        \"Type\" : \"speed\"," +
            "        \"Actual Speed\" : 120," +
            "        \"Speed Limit\" : 100" +
            "    }" +
            "}";

    @Test
    public void testRestDecision() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post("/custom-rest-decision")
                .then()
                .statusCode(200)
                .body("'Should the driver be suspended?'", is("No"));

    }

}
