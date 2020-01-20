package com.auto.APITests.Owner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class SpecialtyTests extends ApiTestPreconditions{
        private Specialty specialty;
        String specUrl = "/specialties";
        String specIdUrl = "/specialties/{id}";

        @BeforeMethod
        public void createSpec() {
            specialty = specCreation();
        }

        @AfterMethod
        public void deleteSpec() {
            specDelete(specialty.getId());
        }

        @Test
        public void specUpdate() {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(specialty)
                    .put(specIdUrl, specialty.getId())
                    .then()
                    .statusCode(204)
                    .log().all();
        }
    @Test
    public void specUpdateError() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialty)
                .put(specIdUrl, 123)
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test
    public void specSreachById() {
        given()
                .get(specIdUrl, specialty.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(specialty.getId()))
                .body("name", equalTo(specialty.getName()))
                .log().all();
        }
    @Test
    public void specSreachByIdError() {
        given()
                .get(specIdUrl, 789)
                .then()
                .statusCode(404)
                .log().all();
    }
    private Specialty specCreation() {
        Specialty lor = new Specialty();
        lor.setName("lor");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(lor)
                .post(specUrl)
                .then()
                .statusCode(201)
                .extract().body()
                .as((Specialty.class));
        }

        public void specDelete(int petId) {
            given()
                    .log().all()
                    .delete(specIdUrl, petId)
                    .then()
                    .statusCode(204);
        }
        @Test
    public void specCreationError() {
        Specialty lor = new Specialty();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(lor)
                .post(specUrl)
                .then()
                .statusCode(400);
    }
    @Test
    public void deleteError(){
        given()
                .log().all()
                .delete(specIdUrl, 404)
                .then()
                .statusCode(404);
    }
}

