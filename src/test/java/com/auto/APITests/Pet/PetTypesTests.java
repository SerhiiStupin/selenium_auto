package com.auto.APITests.Pet;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PetTypesTests {
    private PetType petType;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
    }

    @BeforeMethod
    public void createPetType() {
        petType = postPetTypeTestWithObject();
    }

    @AfterMethod
    public void deletePetType() {
        deletePetTypeByIdTest(petType.getId());
    }
    @Test
    public void petTypeUpdate() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petType)
                .put("/pettypes/{id}", petType.getId())
                .then()
                .statusCode(204)
                .log().all();
    }
    @Test
    public void getPetTypes(){
        given()
                .get("/pettypes")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 6))
                .body("name", hasItems("cat", "hamster", "snake"))
                .log().all();
    }
    @Test
    public void getPetTypeByIdTest() {
        given()
                .get("/pettypes/{id}", petType.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(petType.getId()))
                .body("name", equalTo(petType.getName()))
                .log().all();
    }


    private PetType postPetTypeTestWithObject() {
        PetType bizon = new PetType();
        bizon.setName("bizzone");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bizon)
                .post("/pettypes")
                .then()
                .statusCode(201)
                .extract().body()
                .as((Type) PetType.class);
    }

    public void deletePetTypeByIdTest(int petId) {
        given()
                .log().all()
                .delete("/pettypes/{id}", petId)
                .then()
                .statusCode(204);
    }
}
//    @Test
//    public void getPets(){
//        String pets = given()
//                .when()
//                .get("/pets")
//                .body().print();
//                //.as(NewPet[].class);
////        RestAssured.given()
////                .get("/pets")
////                .then()
////                .statusCode(200)
////                .body("id", hasItems(1, 3, 6))
////                //.body("name", hasItems("Leo", "Betty", "Jewel"))
////                .body("firstName", hasItem("George"))
////                .log().all();
//    }