package com.auto.APITests.Delete;

import com.auto.APITests.Owner.Owner;
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
    Owner owner = new Owner();
    @BeforeMethod
    public void ownerCreate(){
        owner = ownerCreationTest();
    }
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        //RestAssured.baseURI = "http://139.59.149.247/";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
        @AfterMethod
    public void deleteOwner() {
       ownerDelete(Integer.parseInt(owner.getId()));
    }
    @Test
    public void getOwnerIdTest() {
        //int ownerId = 14;
        RestAssured.given()
                .get("/owners/{id}", owner.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(owner.getId()))
                //.body("name", equalTo(owner.getFirstName()))
                .log().all();
    }
    private Owner ownerCreationTest() {
        //double random = Math.random();
        Owner owner = new Owner();
        owner.setId(0);
        owner.setFirstName("Tester");
        owner.setLastName("User");
        owner.setCity("LA");
        owner.setAddress("Street");
        owner.setTelephone("1234567890");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/owners")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(owner.getFirstName()))
                .extract().body()
                .as(Owner.class);
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

    public void ownerDelete(int ownerId){
        //ownerId = newApiOwner.getId();
        RestAssured.given()
                .log().all()
                .delete("/owners/{id}", ownerId)
                .then()
                .statusCode(204);
    }
}
