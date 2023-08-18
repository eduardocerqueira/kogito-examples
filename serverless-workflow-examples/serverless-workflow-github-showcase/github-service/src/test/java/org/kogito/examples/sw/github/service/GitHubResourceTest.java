package org.kogito.examples.sw.github.service;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class GitHubResourceTest {

    @Test
    void addLabels() {
        given().when()
                .body("[ \"bug\", \"documentation\" ]")
                .contentType(ContentType.JSON)
                .post("/repo/john/amazing-repo/pr/1/labels")
                .then()
                .statusCode(200);
    }

    @Test
    void addReviewers() {
        given().when()
                .body("[ \"john\", \"jane\" ]")
                .contentType(ContentType.JSON)
                .post("/repo/john/amazing-repo/pr/1/reviewers")
                .then()
                .statusCode(200);
    }

    @Test
    void fetchFiles() {
        given().when()
                .get("/repo/john/amazing-repo/pr/1/files")
                .then()
                .statusCode(200)
                .body(is("[\"myfile\"]"));
    }
}
