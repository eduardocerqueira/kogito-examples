package org.kie.kogito.examples.payroll;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class TaxRateTest {

    @Test
    public void testEvaluateTaxRateUS() {
        evaluateForCountry("US", Float.valueOf(35.0f));
    }

    @Test
    public void testEvaluateTaxRateUK() {
        evaluateForCountry("UK", Float.valueOf(30.0f));
    }

    @Test
    public void testEvaluateTaxRateDefault() {
        evaluateForCountry("aoc", Float.valueOf(32.0f));
    }

    private void evaluateForCountry(String country, Number result) {
        given()
                .body("{\"employee\" : {\"firstName\" : \"Mark\", \"lastName\" : \"Test\", \"personalId\" : \"xxx-yy-zzz\", \"birthDate\" : \"1995-12-10T14:50:12.123+02:00\", \"address\" : {\"country\" : \""
                        + country + "\", \"city\" : \"Boston\", \"street\" : \"any street 3\", \"zipCode\" : \"10001\"}}}")
                .contentType(ContentType.JSON)
                .when()
                .post("/taxes/rate")
                .then()
                .statusCode(200)
                .body("taxRate", is(result));
    }
}
