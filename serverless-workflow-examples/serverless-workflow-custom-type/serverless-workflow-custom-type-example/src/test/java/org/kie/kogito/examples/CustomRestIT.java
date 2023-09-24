package org.kie.kogito.examples;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.kogito.examples.sw.custom.CalculatorServer;
import org.kie.kogito.serverless.workflow.SWFConstants;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
class CustomRestIT {

    private static CalculatorServer server; 
    
    @BeforeAll 
    static void init() throws IOException {
        server = new CalculatorServer(8082);
    }
    
    
    @AfterAll 
    static void cleanup () throws IOException {
        server.close();
    }
    
    
    @Test
    void testCustomType() {
        testIt("customType");
    }
    
    @Test
    void testCustomFunction() {
        testIt("customFunction");
    }
    

    private void testIt(String path) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of(SWFConstants.DEFAULT_WORKFLOW_VAR, Map.of("dividend", 4, "divisor", 3)))
                .post(path)
                .then()
                .statusCode(201)
                .body("workflowdata.response", is(1));
    }
}
