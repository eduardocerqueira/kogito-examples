package org.kie.kogito.dmn.pmml.springboot.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;

import static org.kie.kogito.dmn.pmml.springboot.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.dmn.pmml.springboot.example.CommonTestUtils.testResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DecisionTreeTest {

    private static final String BASE_PATH = "/Testtree/DecisionTree";
    private static final String TARGET = "decision";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testEvaluateDecisionTreeResult() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        testResult(inputData, BASE_PATH, TARGET, "sunglasses");
    }

    @Test
    void testEvaluateDecisionTreeDescriptive() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        final Map<String, Object> expectedResultMap = new HashMap<>();
        expectedResultMap.put(TARGET, "sunglasses");
        expectedResultMap.put("weatherdecision", "sunglasses");
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

}
