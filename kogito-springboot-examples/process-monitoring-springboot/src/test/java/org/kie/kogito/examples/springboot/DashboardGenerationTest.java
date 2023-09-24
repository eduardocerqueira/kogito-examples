package org.kie.kogito.examples.springboot;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
public class DashboardGenerationTest {

    // restassured needs to know the random port created for test
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testDashboardsListIsAvailable() {
        List<String> dashboards = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get("/monitoring/dashboards/list.json").as(List.class);
        Assertions.assertEquals(3, dashboards.size());
        Assertions.assertTrue(dashboards.stream().anyMatch(s -> s.contains("demo.orderItems.json")));
        Assertions.assertTrue(dashboards.stream().anyMatch(s -> s.contains("Global.json")));
        Assertions.assertTrue(dashboards.stream().anyMatch(s -> s.contains("demo.orders.json")));
    }
}
