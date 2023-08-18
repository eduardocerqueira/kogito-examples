package org.acme;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CustomRestProcessTest {

    @Test
    public void testRestProcess() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of("name", "Paul"))
                .post("/custom-rest-process")
                .peek()
                .then()
                .statusCode(200)
                .body("message", is("Hello Paul"));
    }

}
