package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusIntegrationTest
@QuarkusTestResource(MockServices.class)
class StockProfitIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void stockProfit() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("symbol", "KGTO"))
                .post("/stockprofit")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("workflowdata.symbol", is("KGTO"))
                .body("workflowdata.currentPrice", is(110F))
                .body("workflowdata.profit", is("10%"));
    }
}
