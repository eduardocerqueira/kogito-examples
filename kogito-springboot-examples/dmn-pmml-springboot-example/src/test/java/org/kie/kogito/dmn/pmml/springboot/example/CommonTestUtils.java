package org.kie.kogito.dmn.pmml.springboot.example;

import java.util.Map;

import org.hamcrest.core.IsNull;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonTestUtils {

    public static void testResult(final String inputData,
            final String path,
            final String targetField,
            final Object expectedResult) {
        final Response response = given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post(path);
        System.out.println(response.body().prettyPrint());
        response.then()
                .statusCode(200)
                .body(targetField, is(expectedResult));
    }

    public static void testResultWrongData(final String inputData,
            final String path) {
        final Response response = given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post(path);
        System.out.println(response.body().prettyPrint());
        response.then()
                .statusCode(500)
                .body("exception", isA(String.class));
    }

    public static void testDescriptive(final String inputData,
            final String basePath,
            final String targetField,
            final Map<String, Object> expectedResultMap) {
        String path = basePath + "/descriptive";
        final Response response = given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post(path);
        System.out.println(response.body().prettyPrint());
        Object resultVariables = response
                .then()
                .statusCode(200)
                .body("correlationId", is(new IsNull()))
                .body("segmentationId", is(new IsNull()))
                .body("segmentId", is(new IsNull()))
                .body("segmentIndex", is(0)) // as JSON is not schema aware, here we assert the RAW string
                .body("resultCode", is("OK"))
                .body("resultObjectName", is(targetField))
                .extract()
                .path("resultVariables");
        assertNotNull(resultVariables);
        assertTrue(resultVariables instanceof Map);
        Map<String, Object> mappedResultVariables = (Map) resultVariables;
        expectedResultMap.forEach((key, value) -> {
            assertTrue(mappedResultVariables.containsKey(key));
            assertEquals(value, mappedResultVariables.get(key));
        });

    }

    public static void testDescriptiveWrongData(final String inputData,
            final String basePath) {
        String path = basePath + "/descriptive";
        final Response response = given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post(path);
        System.out.println(response.body().prettyPrint());
        response.then()
                .statusCode(500)
                .body("exception", isA(String.class));
    }

}
