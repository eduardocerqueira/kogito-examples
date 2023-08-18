package org.kie.kogito.dmn.pmml.quarkus.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
class DecisionTreeTest {

    private static final String BASE_PATH = "/Testtree/DecisionTree";
    private static final String TARGET = "decision";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
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
