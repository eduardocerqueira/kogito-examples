package org.kie.kogito.dmn.pmml.springboot.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DMNScoreCardTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testEvaluateScoreCardDMN() {
        String inputData = "{\"age\": 23.0, \"occupation\": \"SKYDIVER\", \"residenceState\": \"AP\", \"validLicense\": true}";
        given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE)))
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post("/TestScoreCardDMN")
                .then()
                .statusCode(200)
                .body("ScoreCardBKM", is("function ScoreCardBKM( age, occupation, residenceState, validLicense )"))
                .body("age", is(comparesEqualTo(23))) // was input
                .body("occupation", is(comparesEqualTo("SKYDIVER"))) // was input
                .body("residenceState", is(comparesEqualTo("AP"))) // was input
                .body("validLicense", is(comparesEqualTo(true))) // was input
                .body("Score", is(comparesEqualTo(21.345)));// real decision output
    }
}
