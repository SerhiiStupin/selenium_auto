package com.auto.APITests.Owner;

import com.auto.PageObjectTests.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.*;

public class OwnerTests {
    Owner owner;
    NewType newType;
    NewVisit newVisit;
    NewPet pet;

    @BeforeMethod
    public void createOwner() {
        owner = ownerCreationTest();
        newType = petTypeAdd();
    }
    @BeforeClass
    public void setUp() {
        //RestAssured.baseURI = "http://localhost";
        RestAssured.baseURI = "http://139.59.149.247";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
//        @AfterMethod
//    public void deleteOwner() {
//       ownerDelete(owner.getId());
//    }

    @Test
    public void getOwners(){
        RestAssured.given()
                .get("/owners")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 2, 3, 10))
                .body("firstName", hasItems("Jean", "David"))
                .body("lastName", hasItems("Estaban", "Black"))
                .body("address", hasItem("2335 Independence La."))
                .log().all();
    }
    @Test
    public void getOwnerByIdTest() {
        RestAssured.given()
                .get("/owners/{id}", owner.getId())
                .then()
                .statusCode(200)
                .body("lastName", equalTo(lastName))
                .body("id", equalTo(Integer.parseInt(owner.getId())))
                .log().all();
    }
    private Owner ownerCreationTest() {
        Owner apiOwner = new Owner();
        apiOwner.setId(0);
        apiOwner.setFirstName("Termi");
        apiOwner.setLastName("Nator");
        apiOwner.setCity("LA");
        apiOwner.setAddress("Street");
        apiOwner.setTelephone("1234567890");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(apiOwner)
                .post("/owners")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(apiOwner.getFirstName()))
                .extract().body()
                .as(Owner.class);
    }

    public NewType petTypeAdd(){
        NewType newType = new NewType();
        newType.setName("alligator");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newType)
                .post("/pettypes")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body()
                .as(NewType.class);
    }
    @Test
    public void visitAdd(){
        NewVisit newVisit = new NewVisit();
        newVisit.setDate("2020/01/01");
        newVisit.setDescription("Test visit");
        newVisit.setPet(pet);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newType)
                .post("/visits")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body()
                .as(NewVisit.class);
    }
    @Test
    //Не работает. 400 статус, чтобы был Pass )))
    public void addPetToOwner() {
        NewPet pet = new NewPet();
        String name = "Tuzik";
        pet.setName(name);
        pet.setBirthDate("2019/10/10");
        pet.setType(newType);
        pet.setOwner(owner);
        pet.setVisits(Collections.singletonList(newVisit));
        pet.setVisits("2020/01/14", "Visit", "id", "pet");
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
    private void ownerDelete(String ownerId){
        RestAssured.given()
                .log().all()
                .delete("/owners/{id}", ownerId)
                .then()
                .statusCode(204);
    }
    @Test
    public void searchOfOwner(){
        String name = "Termi";
        String phone = "1234567890";
        RestAssured.given()
                .get("/owners/*/lastname/{lastName}", lastName)
                .then()
                .statusCode(200)
                .body("lastName", hasItem(lastName))
                .body("firstName", hasItem(name))
                .body("telephone", hasItem(phone))
                .log().all()
                .extract().body();
    }
    @Test
    public void ownerUpdate() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .put("/owners/{id}", owner.getId())
                .then()
                .statusCode(204)
                //.body("id", equalTo(newApiOwner.getId()))
                .log().all();
    }



//    @Test
//    public void ownerUpdate(){
//        RestAssured.given()
//                .get("/owners/{id}", newApiOwner.getId())
//                .then()
//                .statusCode(200)
//                .body("lastName", equalTo(lastName))
//                .body("id", equalTo(Integer.parseInt(newApiOwner.getId())))
//                .log().all();
//    }
//    private NewApiOwner createOwnerforUpdate() {
//        NewApiOwner apiOwner = new NewApiOwner();
//        apiOwner.setId(0);
//        apiOwner.setFirstName("Test");
//        apiOwner.setLastName("User");
//        apiOwner.setCity("Dnipro");
//        apiOwner.setAddress("Address");
//        apiOwner.setTelephone("1234567890");
//        return RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(apiOwner)
//                .post("/owners")
//                .then()
//                .log().all()
//                .statusCode(201)
//                .body("id", notNullValue())
//                .body("firstName", equalTo(apiOwner.getFirstName()))
//                .extract().body()
//                .as(NewApiOwner.class);
//    }
//    private void ownerDelete2(String ownerId){
//        RestAssured.given()
//                .log().all()
//                .delete("/owners/{id}", newApiOwner.getId())
//                .then()
//                .statusCode(204);
//    }

    String lastName = "Nator";
}
