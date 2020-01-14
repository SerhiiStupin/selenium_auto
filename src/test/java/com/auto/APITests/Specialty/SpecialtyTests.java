package com.auto.APITests.Specialty;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class SpecialtyTests {
        private Specialty specialty;

        @BeforeClass
        public void setUp() {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 9966;
            RestAssured.basePath = "/petclinic/api";
        }

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
                    .put("/specialties/{id}", specialty.getId())
                    .then()
                    .statusCode(204)
                    .log().all();
        }
        @Test
        public void specSreachById() {
            given()
                    .get("/specialties/{id}", specialty.getId())
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(specialty.getId()))
                    .body("name", equalTo(specialty.getName()))
                    .log().all();
        }


        private Specialty specCreation() {
            Specialty bizon = new Specialty();
            bizon.setName("bizzone");
            return RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(bizon)
                    .post("/specialties")
                    .then()
                    .statusCode(201)
                    .extract().body()
                    .as((Specialty.class));
        }

        public void specDelete(int petId) {
            given()
                    .log().all()
                    .delete("/specialties/{id}", petId)
                    .then()
                    .statusCode(204);
        }
    }
