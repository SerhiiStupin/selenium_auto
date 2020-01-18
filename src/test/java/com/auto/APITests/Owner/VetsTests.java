package com.auto.APITests.Owner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class VetsTests extends ApiTestPreconditions{
    String lastName = "Bolit";
    Vets vets = new Vets();

    @BeforeMethod
    public void createSpec() {
        vets = vetCreation();
    }

    @AfterMethod
    public void deleteSpec() {
        vetDelete(String.valueOf(vets.getId()));
    }
    @Test
    public void getVets(){
        RestAssured.given()
                .get("/vets")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 6))
                .body("firstName", hasItems("Helen", "Linda"))
                .body("lastName", hasItems("Ortega", "Carter"))
                .log().all();
    }
    @Test
    public void getVetsError(){
        RestAssured.given()
                .get("/vetss")
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test
    public void getVetByIdTest() {
        RestAssured.given()
                .get("/vets/{id}", vets.getId())
                .then()
                .statusCode(200)
                .body("lastName", equalTo(lastName))
                .body("id", equalTo(vets.getId()))
                .log().all();
    }
    @Test
    public void getVetByIdTest404() {
        RestAssured.given()
                .get("/vets/{id}", 0.5)
                .then()
                .statusCode(400)
                .log().all();
    }
    private Vets vetCreation() {
        Vets vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("i");
        vets.setLastName(lastName);
        vets.setSpecialties(spec);
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .post("/vets")
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
                .delete("/vets/{id}", ownerId)
                .then()
                .statusCode(204);
    }
    @Test
    private void vetUpdate() {
        int id = 6;
        Vets vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("qwertyuio");
        vets.setLastName(lastName);
        vets.setSpecialties(spec);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .put("/vets/{id}", id)
                .then()
                .log().all()
                .statusCode(204)
                .log().all();
    }
    @Test
    public void vetCreationError() {
        Vets vets = new Vets();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .post("/vets")
                .then()
                .log().all()
                .statusCode(400);
    }
    @Test
    public void vetDeleteError(){
        RestAssured.given()
                .log().all()
                .delete("/vets/{id}", 404)
                .then()
                .statusCode(404);
    }
    @Test
    private void vetUpdateError() {
        int id = 628;
        Vets vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("qwertyuio");
        vets.setLastName(lastName);
        vets.setSpecialties(spec);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .put("/vets/{id}", id)
                .then()
                .log().all()
                .statusCode(404)
                .log().all();
    }
}
