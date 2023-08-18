package org.kie.kogito.dmn.pmml.quarkus.example;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class DMNTreeTest {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testEvaluateTreeDMN() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post("/TestTreeDMN")
                .then()
                .statusCode(200)
                .body("TestTreeBKM", is("function TestTreeBKM( humidity, temperature )"))
                .body("temperature", is(Float.valueOf("30")))
                .body("humidity", is(Float.valueOf("10")))
                .body("Decision", is("sunglasses"));
    }
}
