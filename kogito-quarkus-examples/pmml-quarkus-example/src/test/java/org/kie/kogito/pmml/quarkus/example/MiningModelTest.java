package org.kie.kogito.pmml.quarkus.example;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
class MiningModelTest {

    private static final String BASE_PATH = "/Testminingmodel/PredicatesMining";
    private static final String TARGET = "categoricalResult";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testEvaluatePredicatesMiningResult() {
        String inputData = "{\"residenceState\":\"AP\", " +
                "\"validLicense\":true, " +
                "\"occupation\":\"ASTRONAUT\", " +
                "\"categoricalY\":\"classA\", " +
                "\"categoricalX\":\"red\", " +
                "\"variable\":6.6, " +
                "\"age\":25.0}";
        testResult(inputData, BASE_PATH, TARGET, 1.381666666666666f);
    }

    @Test
    void testEvaluatePredicatesMiningDescriptive() {
        String inputData = "{\"residenceState\":\"AP\", " +
                "\"validLicense\":true, " +
                "\"occupation\":\"ASTRONAUT\", " +
                "\"categoricalY\":\"classA\", " +
                "\"categoricalX\":\"red\", " +
                "\"variable\":6.6, " +
                "\"age\":25.0}";
        final Map<String, Object> expectedResultMap = Collections.singletonMap(TARGET, 1.381666666666666f);
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }
}
