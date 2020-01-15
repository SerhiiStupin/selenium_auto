package com.auto.APITests.Owner;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItems;

public class PetTest {
    NewPet pet;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        //RestAssured.baseURI = "http://139.59.149.247";
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
                .body("name", hasItems("Samantha", "Jewel"))
                .log().all();
    }
    @Test
    public void deletePetTypeByIdTest() {
        int petId = 3;
        RestAssured.given()
                .log().all()
                .delete("/pets/{id}", petId)
                .then()
                .statusCode(204);
    }
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
//    @Test
//    public void postPetTypeTestWithObject() {
//        String id = "2";
//        NewPet pet = new NewPet();
//        NewType petType = new NewType();
//        Owner owner = new Owner();
//        //NewOwner newOwner = new NewOwner();
//        String name = "Tuzik";
//        String type = "dog";
//        pet.setName(name);
//        pet.setBirthDate("2019/10/10");
//        petType.setName(type);
//        pet.getOwner();
//        owner.getId().equals(id);
//        owner.getAddress().equals("638 Cardinal Ave.");
//        owner.getFirstName().equals("Betty");
//        owner.getLastName().equals("Davis");
//        owner.getCity().equals("Sun Prairie");
//        owner.getTelephone().equals("6085551749");
////    newApiOwner.setFirstName("Betty");
////    newApiOwner.setLastName("Davis");
////    newApiOwner.setAddress("638 Cardinal Ave.");
////    newApiOwner.setTelephone("6085551749");
////    newApiOwner.setCity("Sun Prairie");
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(pet)
//                .post("/pets")
//                .then()
//                .log().all()
//                .statusCode(201)
//                .extract().body()
//                .as(NewPet.class);
//    }
