package com.auto.APITests.Owner;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("PetClinic")
@Feature("Pettypes API")
public class SpecialtyTests extends ApiTestPreconditions{
        private Specialty specialty;

        @BeforeMethod
        @Step("Spec creating")
        public void createSpec() {
            specialty = specCreation();
        }

        @AfterClass
        @Step("Spec deleting")
        public void deleteSpec() {
            specDelete(specialty.getId());
        }

    @Test(description = "Update of the spec")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update of the spec")
    @TmsLink("spec.com")
    @Issue("Bug-40")
    public void specUpdate() {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(specialty)
                    .put(specIdUrl, specialty.getId())
                    .then()
                    .statusCode(204)
                    .log().all();
        }
    @Test(description = "Update of the incorrect spec")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update of the incorrect spec")
    @TmsLink("spec.com")
    @Issue("Bug-41")
    public void specUpdateError() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialty)
                .put(specIdUrl, 123)
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test(description = "Get spec by id and name")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get spec by id and name")
    @TmsLink("spec.com")
    @Issue("Bug-42")
    public void specSreachById() {
        given()
                .get(specIdUrl, specialty.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(specialty.getId()))
                .body("name", equalTo(specialty.getName()))
                .log().all();
        }
    @Test(description = "Get spec by incorrect id ")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get spec by incorrect id ")
    @TmsLink("spec.com")
    @Issue("Bug-43")
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

    @Test(description = "Spec creation with incorrect data")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Spec creation with incorrect data")
    @TmsLink("spec.com")
    @Issue("Bug-44")
    public void specCreationError() {
        Specialty lor = new Specialty();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(lor)
                .post(specUrl)
                .then()
                .statusCode(400);
    }
    @Test(description = "Deleting of the incorrect spec")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Deleting of the incorrect spec")
    @TmsLink("spec.com")
    @Issue("Bug-45")
    public void deleteError(){
        given()
                .log().all()
                .delete(specIdUrl, 404)
                .then()
                .statusCode(404);
    }
}

