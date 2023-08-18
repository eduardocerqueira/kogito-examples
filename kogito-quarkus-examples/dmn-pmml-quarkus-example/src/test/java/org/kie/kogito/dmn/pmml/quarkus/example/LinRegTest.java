package org.kie.kogito.dmn.pmml.quarkus.example;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.dmn.pmml.quarkus.example.CommonTestUtils.testResult;

@QuarkusTest
public class LinRegTest {

    private static final String BASE_PATH = "/Testregression/LinReg";
    private static final String TARGET = "fld4";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testEvaluateLinRegResult() {
        String inputData = "{\"fld1\":3.0, \"fld2\":2.0, \"fld3\":\"y\"}";
        testResult(inputData, BASE_PATH, TARGET, 52.5f);
    }

    @Test
    void testEvaluateLinRegDescriptive() {
        String inputData = "{\"fld1\":3.0, \"fld2\":2.0, \"fld3\":\"y\"}";
        final Map<String, Object> expectedResultMap = Collections.singletonMap(TARGET, 52.5f);
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

}
