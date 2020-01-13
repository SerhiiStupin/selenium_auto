package com.auto.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OwnerPreCond {
    NewApiOwner newApiOwner;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
    //    @AfterMethod
//    public void deleteOwner() {
//       ownerDelete(apiOwner.getId());
//    }

    @Test
    public NewApiOwner ownerCreationTest() {
        //double random = Math.random();
        NewApiOwner apiOwner = new NewApiOwner();
        apiOwner.setId(1000);
        apiOwner.setFirstName("Tester");
        apiOwner.setLastName("User");
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
