package org.kogito.serverless.examples;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.kogito.serverless.examples.input.Country;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FunctionsIT {

    @Test
    public void testCountriesFunction() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(new Country("Germany")))
                .when().post("/country")
                .then()
                .statusCode(200)
                .body("name", CoreMatchers.equalTo("Germany"));

    }

    @Test
    public void testPopulationFunction() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(new Country("USA")))
                .when().post("/population")
                .then()
                .statusCode(200)
                .body("name", CoreMatchers.equalTo("USA"));

    }

    @Test
    public void testClassificationFunction() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(new Country("Serbia")))
                .when().post("/classify")
                .then()
                .statusCode(200)
                .body("name", CoreMatchers.equalTo("Serbia"));

    }
}
