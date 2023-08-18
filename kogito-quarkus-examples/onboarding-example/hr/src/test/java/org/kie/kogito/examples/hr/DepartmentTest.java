package org.kie.kogito.examples.hr;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class DepartmentTest {

    @Test
    public void testDepartmentUS() {
        evaluateForCountry("US", "John Doe");
    }

    @Test
    public void testDepartmentUK() {
        evaluateForCountry("UK", "Teresa April");
    }

    private void evaluateForCountry(String country, String result) {
        given()
                .body("{\"employee\" : {\"firstName\" : \"Mark\", \"lastName\" : \"Test\", \"personalId\" : \"xxx-yy-zzz\", \"birthDate\" : \"1995-12-10T14:50:12.123+02:00\", \"address\" : {\"country\" : \""
                        + country + "\", \"city\" : \"Boston\", \"street\" : \"any street 3\", \"zipCode\" : \"10001\"}}}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/department/first")
                .then()
                .body("manager", is(result));
    }
}
