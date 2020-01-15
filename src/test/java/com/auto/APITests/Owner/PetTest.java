package com.auto.APITests.Owner;

import com.auto.PageObjectTests.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class PetTest {
    NewPet pet;
    @BeforeClass
    public void setUp() {
        //RestAssured.baseURI = "http://localhost";
        RestAssured.baseURI = "http://139.59.149.247";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
//    @BeforeMethod
//    public void createPet() {
//        getPetTypeByIdTest();
//    }
//    @AfterMethod
//    public void deletePet() {
//        deletePetTypeByIdTest(pet.getId());
//    }
    @Test
    public void petGetTest() {
        NewPet pet = new NewPet();
        RestAssured.given()
                .given()
                .get("/pets")
                .then()
                .body("name", hasItem("Test"))
                .log().all();
    }
//    @Test
//    public void getPetTypeByIdTest() {
//        RestAssured.given()
//                .get("/pets/{id}", pet.getId())
//                .then()
//                .statusCode(200)
//                .body("id", (ResponseAwareMatcher<Response>) equalTo(pet.getId()))
//                .body("name", (ResponseAwareMatcher<Response>) equalTo(pet.getName()))
//                .log().all();
//    }
//    private NewPet postPetTypeTestWithObject() {
//        NewPet pet = new NewPet();
//        NewType petType = new NewType();
//        NewApiOwner newApiOwner = new NewApiOwner();
//        String name = "Tuzik";
//        String type = "dog";
//        pet.setName(name);
//        pet.setBirthDate("2019/10/10");
//        petType.setName(type);
//        newApiOwner.setFirstName("Betty");
//        newApiOwner.setLastName("Davis");
//        newApiOwner.setAddress("638 Cardinal Ave.");
//        newApiOwner.setTelephone("6085551749");
//        return RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(pet)
//                .post("/pets")
//                .then()
//                .statusCode(201)
//                .extract().body()
//                .as(NewPet.class);
//    }
@Test
public void postPetTypeTestWithObject() {
    NewPet pet = new NewPet();
    NewType petType = new NewType();
    NewApiOwner newApiOwner = new NewApiOwner();
    String name = "Tuzik";
    String type = "dog";
    pet.setName(name);
    pet.setBirthDate("2019/10/10");
    petType.setName(type);
    newApiOwner.setFirstName("Betty");
    newApiOwner.setLastName("Davis");
    newApiOwner.setAddress("638 Cardinal Ave.");
    newApiOwner.setTelephone("6085551749");
    newApiOwner.setCity("Sun Prairie");
    RestAssured.given()
            .contentType(ContentType.JSON)
            .body(pet)
            .post("/pets")
            .then()
            .log().all()
            .statusCode(201)
            .extract().body()
            .as(NewPet.class);
}

    public void deletePetTypeByIdTest(int petId) {
        RestAssured.given()
                .log().all()
                .delete("/pets/{id}", petId)
                .then()
                .statusCode(204);
    }
}
