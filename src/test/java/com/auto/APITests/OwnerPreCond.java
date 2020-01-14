package com.auto.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OwnerPreCond {
    NewApiOwner newApiOwner = new NewApiOwner();
    @BeforeMethod
    public void ownerCreate(){
        ownerCreationTest();
    }
    @BeforeClass
    public void setUp() {
        //RestAssured.baseURI = "http://localhost";
        RestAssured.baseURI = "http://139.59.149.247/";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
        @AfterMethod
    public void deleteOwner() {
       ownerDelete(newApiOwner.getId());
    }
    @Test
    public void ownerCreationTest() {
        //double random = Math.random();
        NewApiOwner newApiOwner = new NewApiOwner();
        newApiOwner.setId(0);
        newApiOwner.setFirstName("Tester");
        newApiOwner.setLastName("User");
        newApiOwner.setCity("LA");
        newApiOwner.setAddress("Street");
        newApiOwner.setTelephone("1234567890");
        //return
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newApiOwner)
                .post("/owners")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(newApiOwner.getFirstName()))
                .extract().body()
                .as(NewApiOwner.class);
    }

//    @Test
//    public NewApiOwner ownerCreationTest() {
//        //double random = Math.random();
//        NewApiOwner apiOwner = new NewApiOwner();
//        apiOwner.setId(1000);
//        apiOwner.setFirstName("Tester");
//        apiOwner.setLastName("User");
//        apiOwner.setCity("LA");
//        apiOwner.setAddress("Street");
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


        @Test
    public void getOwnerIdTest() {
        //int ownerId = 14;
        RestAssured.given()
                .get("/owners/{id}", newApiOwner.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(newApiOwner.getId()))
                //.body("name", equalTo(owner.getFirstName()))
                .log().all();
    }
    @Test
    public void ownerDelete(int ownerId){
        //ownerId = newApiOwner.getId();
        RestAssured.given()
                .log().all()
                .delete("/owners/{id}", ownerId)
                .then()
                .statusCode(204);
    }
}
