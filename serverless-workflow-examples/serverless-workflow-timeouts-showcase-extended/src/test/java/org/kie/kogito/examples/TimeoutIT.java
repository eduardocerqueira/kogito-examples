package org.kie.kogito.examples;

import java.util.concurrent.TimeUnit;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

@QuarkusIntegrationTest
class TimeoutIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static final String SWITCH_STATE_TIMEOUT_WORKFLOW_PATH = "switch_state_timeouts";
    public static final String CALLBACK_STATE_TIMEOUT_WORKFLOW_PATH = "callback_state_timeouts";
    public static final String EVENT_STATE_TIMEOUT_WORKFLOW_PATH = "event_state_timeouts";
    public static final String TIMEOUT_WORKFLOW_PATH = "workflow_timeout";

    @Test
    void testSwitchStateWorkflow() {
        testWorkflow(SWITCH_STATE_TIMEOUT_WORKFLOW_PATH);
    }

    @Test
    void testCallbackStateWorkflow() {
        testWorkflow(CALLBACK_STATE_TIMEOUT_WORKFLOW_PATH);
    }

    @Test
    void testEventStateWorkflow() {
        testWorkflow(EVENT_STATE_TIMEOUT_WORKFLOW_PATH);
    }
    
    @Test
    void testWorkflow() {
        testWorkflow(TIMEOUT_WORKFLOW_PATH);
    }

    private void testWorkflow(String workflowPath) {
        //create the workflow instance
        String id = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{}").when()
                .post(workflowPath)
                .then()
                .statusCode(201)
                .extract()
                .body().path("id");

        //check workflow instance was created and active
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(workflowPath + "/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id));

        //check the workflow instance was timed-out and completed
        await()
                .pollDelay(15, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES)
                .untilAsserted(() -> {
                    given()
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .get(workflowPath + "/{id}", id)
                            .then()
                            .statusCode(404);
                });
    }
}
