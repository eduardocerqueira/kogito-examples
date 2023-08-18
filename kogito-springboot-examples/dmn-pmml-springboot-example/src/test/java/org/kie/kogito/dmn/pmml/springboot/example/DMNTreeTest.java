package org.kie.kogito.dmn.pmml.springboot.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DMNTreeTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
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
