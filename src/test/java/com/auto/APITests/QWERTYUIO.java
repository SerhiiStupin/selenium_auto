package com.auto.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class QWERTYUIO {
    @BeforeClass
    public void setUp() {
        //RestAssured.baseURI = "http://localhost";
        RestAssured.baseURI = "http://139.59.149.247/";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
    @Test
    public void ownerCreationTest() {
        //double random = Math.random();
        NewApiOwner apiOwner = new NewApiOwner();
        apiOwner.setId(1000);
        apiOwner.setFirstName("Tester");
        apiOwner.setLastName("User");
        apiOwner.setCity("LA");
        apiOwner.setAddress("Street");
        apiOwner.setTelephone("1234567890");
        //return
                RestAssured.given()
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
    private NewApiOwner ownerCreationTest2() {
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
}
