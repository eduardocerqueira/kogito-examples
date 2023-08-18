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
class SampleMineTest {

    private static final String BASE_PATH = "/Testtree/SampleMine";
    private static final String TARGET = "decision";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
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
