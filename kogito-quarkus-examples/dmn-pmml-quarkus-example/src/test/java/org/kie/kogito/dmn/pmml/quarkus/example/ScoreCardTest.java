package org.kie.kogito.dmn.pmml.quarkus.example;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
public class ScoreCardTest {

    private static final String BASE_PATH = "/Testscorecard/SampleScore";
    private static final String TARGET = "overallScore";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testEvaluateScoreCardResult() {
        String inputData = "{\"age\": 23.0, \"occupation\": \"SKYDIVER\", \"residenceState\": \"AP\", \"validLicense\": true}";
        testResult(inputData, BASE_PATH, TARGET, 21.345f);
    }

    @Test
    public void testEvaluateScoreCardResultDescriptive() {
        String inputData = "{\"age\": 23.0, \"occupation\": \"SKYDIVER\", \"residenceState\": \"AP\", \"validLicense\": true}";
        final Map<String, Object> expectedResultMap = Collections.singletonMap(TARGET, 21.345f);
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }
}
