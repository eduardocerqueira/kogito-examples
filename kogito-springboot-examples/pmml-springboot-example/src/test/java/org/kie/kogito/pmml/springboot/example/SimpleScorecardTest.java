package org.kie.kogito.pmml.springboot.example;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;

import static org.kie.kogito.pmml.springboot.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.pmml.springboot.example.CommonTestUtils.testResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SimpleScorecardTest {

    private static final String BASE_PATH = "/Testscorecard/SimpleScorecard";
    private static final String TARGET = "score";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
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
