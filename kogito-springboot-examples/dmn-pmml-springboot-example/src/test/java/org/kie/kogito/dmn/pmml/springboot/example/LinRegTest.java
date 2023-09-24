package org.kie.kogito.dmn.pmml.springboot.example;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;

import static org.kie.kogito.dmn.pmml.springboot.example.CommonTestUtils.testDescriptive;
import static org.kie.kogito.dmn.pmml.springboot.example.CommonTestUtils.testDescriptiveWrongData;
import static org.kie.kogito.dmn.pmml.springboot.example.CommonTestUtils.testResult;
import static org.kie.kogito.dmn.pmml.springboot.example.CommonTestUtils.testResultWrongData;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LinRegTest {

    private static final String BASE_PATH = "/Testregression/LinReg";
    private static final String TARGET = "fld4";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testEvaluateLinRegResult() {
        String inputData = "{\"fld1\":3.0, \"fld2\":2.0, \"fld3\":\"y\"}";
        testResult(inputData, BASE_PATH, TARGET, 52.5f);
    }

    @Test
    void testEvaluateLinRegResultWrongData() {
        String inputData = "{\"fld1\":\"wrong-input\", \"fld2\":2.0, \"fld3\":\"y\"}";
        testResultWrongData(inputData, BASE_PATH);
    }

    @Test
    void testEvaluateLinRegDescriptive() {
        String inputData = "{\"fld1\":3.0, \"fld2\":2.0, \"fld3\":\"y\"}";
        final Map<String, Object> expectedResultMap = Collections.singletonMap(TARGET, 52.5f);
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

    @Test
    void testEvaluateLinRegDescriptiveWrongData() {
        String inputData = "{\"fld1\":\"wrong-input\", \"fld2\":2.0, \"fld3\":\"y\"}";
        testDescriptiveWrongData(inputData, BASE_PATH);
    }
}
