package org.acme.it;

import java.util.List;

import org.acme.QueryRequest;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusIntegrationTest
class QueryServiceResourceIT {

    private static final String QUERY_SERVICE_URL = "/query-service";
    private static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";
    private static final String QUERY = "THE QUERY TO ANSWER";
    private static final String RESPONSE = "THE RESPONSE TO SEND";

    @Test
    void queryProcessCycle() {
        // prepare and send a query request
        String queryRequestJson = "{" +
                "\"processInstanceId\": \"" + PROCESS_INSTANCE_ID + "\"," +
                "\"query\": \"" + QUERY + "\"" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queryRequestJson)
                .post(QUERY_SERVICE_URL + "/sendQuery")
                .then()
                .statusCode(200);

        // verify that the formulated query is there.
        List<QueryRequest> queryRequests = getCurrentRequests();
        assertThat(queryRequests).hasSize(1);
        QueryRequest queryRequest = queryRequests.get(0);
        assertThat(queryRequest.getProcessInstanceId()).isEqualTo(PROCESS_INSTANCE_ID);
        assertThat(queryRequest.getQuery()).isEqualTo(QUERY);

        // resolve the query
        String resolveRequestJson = "{" +
                "\"processInstanceId\": \"" + PROCESS_INSTANCE_ID + "\"," +
                "\"queryResponse\": \"" + RESPONSE + "\"" +
                "}";

        String resolveQueryPath = QUERY_SERVICE_URL + "/resolveQuery";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(resolveRequestJson)
                .post(resolveQueryPath)
                .then()
                .statusCode(200);

        // verify the query is no longer in the query service.
        queryRequests = getCurrentRequests();
        assertThat(queryRequests).isEmpty();
    }

    private List<QueryRequest> getCurrentRequests() {
        JsonPath currentRequests = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(QUERY_SERVICE_URL)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();
        return currentRequests.getList("", QueryRequest.class);
    }
}