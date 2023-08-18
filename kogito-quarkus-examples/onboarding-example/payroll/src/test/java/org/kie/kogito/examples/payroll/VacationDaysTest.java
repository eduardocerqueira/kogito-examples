package org.kie.kogito.examples.payroll;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class VacationDaysTest {

    @Test
    public void testEvaluateVacationDaysUS() {
        evaluateForCountry("US", 10);
    }

    @Test
    public void testEvaluateVacationDaysIT() {
        evaluateForCountry("IT", 20);
    }

    private void evaluateForCountry(String country, Number result) {
        given()
                .body("{\"employee\" : {\"firstName\" : \"Mark\", \"lastName\" : \"Test\", \"personalId\" : \"xxx-yy-zzz\", \"birthDate\" : \"1995-12-10T14:50:12.123+02:00\", \"address\" : {\"country\" : \""
                        + country + "\", \"city\" : \"Boston\", \"street\" : \"any street 3\", \"zipCode\" : \"10001\"}}}")
                .contentType(ContentType.JSON)
                .when()
                .post("/vacations/days")
                .then()
                .statusCode(200)
                .body("vacationDays", is(result));
    }
}
