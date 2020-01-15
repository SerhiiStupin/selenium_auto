package com.auto.APITests.Owner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class OwnerTests {
    NewApiOwner newApiOwner;

    @BeforeMethod
    public void createOwner() {
        newApiOwner = ownerCreationTest();
    }
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
        @AfterMethod
    public void deleteOwner() {
       ownerDelete(newApiOwner.getId());
    }
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
                .get("/owners/{id}", newApiOwner.getId())
                .then()
                .statusCode(200)
                .body("lastName", equalTo(lastName))
                .body("id", equalTo(Integer.parseInt(newApiOwner.getId())))
                .log().all();
    }
    private NewApiOwner ownerCreationTest() {
        NewApiOwner apiOwner = new NewApiOwner();
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
                .as(NewApiOwner.class);
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
                .body(newApiOwner)
                .put("/owners/{id}", newApiOwner.getId())
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
