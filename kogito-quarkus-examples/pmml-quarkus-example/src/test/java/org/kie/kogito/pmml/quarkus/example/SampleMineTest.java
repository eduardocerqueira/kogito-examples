package org.kie.kogito.pmml.quarkus.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
class SampleMineTest {

    private static final String BASE_PATH = "/Testtree/SampleMine";
    private static final String TARGET = "decision";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testEvaluateSampleMineResult() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        testResult(inputData, BASE_PATH, TARGET, "sunglasses");
    }

    @Test
    void testEvaluateSampleMineDescriptive() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        final Map<String, Object> expectedResultMap = new HashMap<>();
        expectedResultMap.put(TARGET, "sunglasses");
        expectedResultMap.put("weatherdecision", "sunglasses");
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

}
