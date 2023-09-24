package org.kie.kogito.dmn.pmml.quarkus.example;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class DMNMiningModelTest {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testEvaluateMiningModelDMN() {
        String inputData = "{\"input1\":200.0, \"input2\":-1.0, \"input3\":2.0}";
        given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE)))
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post("/TestMiningModelDMN")
                .then()
                .statusCode(200)
                .body("SumMiningModelBKM", is("function SumMiningModelBKM( input1, input2, input3 )"))
                .body("input1", is(comparesEqualTo(200))) // was input
                .body("input2", is(comparesEqualTo(-1))) // was input
                .body("input3", is(comparesEqualTo(2))) // was input
                .body("Decision", is(comparesEqualTo(-299))) // real decision output
        ;
    }
}
