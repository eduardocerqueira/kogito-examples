package org.acme;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CustomRestRulesTest {

    @Test
    public void testRules() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of("strings", List.of("hello")))
                .post("/custom-rest-rules")
                .peek()
                .then()
                .statusCode(200)
                .body(is("[\"hello\",\"world\"]"));
    }

}
