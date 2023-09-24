package org.kie.kogito.pmml.springboot.example;

import java.util.Collections;
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
class LinRegTest {

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
    void testEvaluateLinRegDescriptive() {
        String inputData = "{\"fld1\":3.0, \"fld2\":2.0, \"fld3\":\"y\"}";
        final Map<String, Object> expectedResultMap = Collections.singletonMap(TARGET, 52.5f);
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

}
