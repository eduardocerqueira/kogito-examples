package org.kie.kogito.pmml.quarkus.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
class SimpleScorecardTest {

    private static final String BASE_PATH = "/Testscorecard/SimpleScorecard";
    private static final String TARGET = "score";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testEvaluateSimpleScorecardResult() {
        String inputData = "{\"input1\":5.0, \"input2\":-10.0}";
        testResult(inputData, BASE_PATH, TARGET, -15.0f);
    }

    @Test
    void testEvaluateSimpleScorecardDescriptive() {
        String inputData = "{\"input1\":5.0, \"input2\":-10.0}";
        final Map<String, Object> expectedResultMap = new HashMap<>();
        expectedResultMap.put(TARGET, -15.0f);
        expectedResultMap.put("Reason Code 1", "Input1ReasonCode");
        expectedResultMap.put("Reason Code 2", "Input2ReasonCode");
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

}
