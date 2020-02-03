package com.auto.APITests.Owner;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;

@Epic("PetClinic")
@Feature("Vets API")
public class VetsTests extends ApiTestPreconditions{
    private Vets vets = new Vets();

    @BeforeMethod
    @Step("Vets creating")
    public void createSpec() {
        vets = vetCreation();
    }

    @AfterMethod
    @Step("Vets deleting")
    public void deleteSpec() {
        vetDelete(String.valueOf(vets.getId()));
    }

    @Test(description = "Get vets by first name and last name")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get vets by first name and last name")
    @TmsLink("vets.com")
    @Issue("Bug-50")
    public void getVets(){
        RestAssured.given()
                .get(vetsUrl)
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 6))
                .body("firstName", hasItems("Helen", "Linda"))
                .body("lastName", hasItems("Ortega", "Carter"))
                .log().all();
    }
    @Test(description = "Get incorrect vets")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Get incorrect vets")
    @TmsLink("vets.com")
    @Issue("Bug-51")
    public void getVetsError(){
        RestAssured.given()
                .get("/vetss")
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test(description = "Get created vet by id and lastName")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get created vet by id and lastName")
    @TmsLink("vets.com")
    @Issue("Bug-52")
    public void getVetByIdTest() {
        RestAssured.given()
                .get(vetsIdUrl, vets.getId())
                .then()
                .statusCode(200)
                .body("lastName", equalTo(vetLastName))
                .body("id", equalTo(vets.getId()))
                .log().all();
    }
    @Test(description = "Get created vet by incorrect id")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Get created vet by incorrect id")
    @TmsLink("vets.com")
    @Issue("Bug-53")
    public void getVetByIdTest404() {
        RestAssured.given()
                .get(vetsIdUrl, 0.5)
                .then()
                .statusCode(400)
                .log().all();
    }
    private Vets vetCreation() {
        Vets vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("i");
        vets.setLastName(vetLastName);
        vets.setSpecialties(spec);
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .post(vetsUrl)
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(vets.getFirstName()))
                .extract().body()
                .as(Vets.class);
    }
    private void vetDelete(String ownerId){
        RestAssured.given()
                .log().all()
                .delete(vetsIdUrl, ownerId)
                .then()
                .statusCode(204);
    }
    @Test(description = "Update of the vet")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update of the vet")
    @TmsLink("vets.com")
    @Issue("Bug-54")
    private void vetUpdate() {
        int id = 6;
        Vets vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("qwertyuio");
        vets.setLastName(vetLastName);
        vets.setSpecialties(spec);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .put(vetsIdUrl, id)
                .then()
                .log().all()
                .statusCode(204)
                .log().all();
    }
    @Test(description = "Vet creation with incorrect data")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Vet creation with incorrect data")
    @TmsLink("vets.com")
    @Issue("Bug-55")
    public void vetCreationError() {
        Vets vets = new Vets();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .post(vetsUrl)
                .then()
                .log().all()
                .statusCode(400);
    }
    @Test(description = "Vet deleting")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Vet deleting")
    @TmsLink("vets.com")
    @Issue("Bug-56")
    public void vetDeleteError(){
        RestAssured.given()
                .log().all()
                .delete(vetsIdUrl, 404)
                .then()
                .statusCode(404);
    }
    @Test(description = "Incorrect vet update")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Incorrect vet update")
    @TmsLink("vets.com")
    @Issue("Bug-57")
    private void vetUpdateError() {
        int id = 628;
        Vets vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("qwertyuio");
        vets.setLastName(vetLastName);
        vets.setSpecialties(spec);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .put(vetsIdUrl, id)
                .then()
                .log().all()
                .statusCode(404)
                .log().all();
    }
}
