package org.kie.kogito.dmn.pmml.quarkus.example;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
public class MiningModelTest {

    private static final String BASE_PATH = "/Testminingmodelsummed/MiningModelSum";
    private static final String TARGET = "result";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testEvaluateMiningModelResult() {
        String inputData = "{\"input1\":200.0, \"input2\":-1.0, \"input3\":2.0}";
        testResult(inputData, BASE_PATH, TARGET, -299.0f);
    }

    @Test
    void testEvaluateMiningModelResultDescriptive() {
        String inputData = "{\"input1\":200.0, \"input2\":-1.0, \"input3\":2.0}";
        final Map<String, Object> expectedResultMap = Collections.singletonMap(TARGET, -299.0f);
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }
}
