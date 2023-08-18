package org.kie.kogito.traffic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoApplication.class)
public class TrafficProcessIT {

    public static final BigDecimal SPEED_LIMIT = new BigDecimal(100);

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    public void setup() {
        RestAssured.port = randomServerPort;
    }

    @Test
    public void testTrafficViolationEmbeddedDecisionOnQuarkus() {
        testTrafficProcess("traffic", "12-345", 120d, "no", true, true);
        testTrafficProcess("traffic", "12-15", 140d, "yes", true, false);
        testTrafficProcess("traffic", "0-150", 140d, null, false, false);
    }

    private void testTrafficProcess(String processId, String driverId, Double speed, String suspended, boolean validLicense, boolean validatedLicense) {
        Map<String, Object> request = new HashMap<>();
        request.put("driverId", driverId);
        request.put("violation", new Violation("speed", SPEED_LIMIT, new BigDecimal(speed)));
        ValidatableResponse body = given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/" + processId)
                .then()
                .statusCode(201)
                .body("driver.ValidLicense", is(validLicense));
        if (suspended != null) {
            body.body("validated.ValidLicense", is(validatedLicense))
                    .body("validated.Suspended", is(suspended));
        } else {
            body.body("validated", nullValue());
        }
    }
}
