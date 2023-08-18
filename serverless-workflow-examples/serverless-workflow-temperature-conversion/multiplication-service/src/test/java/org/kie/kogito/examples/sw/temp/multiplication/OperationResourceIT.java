package org.kie.kogito.examples.sw.temp.multiplication;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusIntegrationTest
class OperationResourceIT {

    @Test
    void testRestExample() {
        final OperationResource.Result result = given()
                .contentType(ContentType.JSON)
                .when()
                .body(new MultiplicationOperation(2, 2))
                .post("/")
                .then()
                .statusCode(200).extract().as(OperationResource.Result.class);
        assertThat(result.getProduct(), is(4f));
    }
}
