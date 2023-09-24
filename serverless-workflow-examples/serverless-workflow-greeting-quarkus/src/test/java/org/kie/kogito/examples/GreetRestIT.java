package org.kie.kogito.examples;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusIntegrationTest
class GreetRestIT {

    @Test
    void testEnglish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\" : \"John\", \"language\":\"English\"}").when()
                .post("/jsongreet")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Hello"));
    }

    @Test
    void testSpanish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\" : \"Javierito\", \"language\":\"Spanish\"}").when()
                .post("/jsongreet")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Saludos"));
    }

    @Test
    void testDefaultLanguage() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\" : \"John\"}").when()
                .post("/jsongreet")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Hello"));
    }

    @Test
    void testUnsupportedLanguage() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"name\" : \"Jan\", \"language\":\"Czech\"}").when()
                .post("/jsongreet")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Hello"));
    }
}
