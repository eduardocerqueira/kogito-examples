package org.acme;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CustomRestPredictionTest {

    @Test
    public void testRestPrediction() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of(
                        "fld1", 3.0,
                        "fld2", 2.0,
                        "fld3", "y"))
                .post("/custom-rest-prediction")
                .peek()
                .then()
                .statusCode(200)
                .body("fld4", is(52.5f));
    }

}
