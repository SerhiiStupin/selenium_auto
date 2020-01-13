package com.auto.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OwnerTestPerPostConditions {
    private ApiOwner apiOwner;
//    @BeforeMethod
//    public void createOwner() {
//        apiOwner = ownerCreationTest();
//    }
//    @AfterMethod
//    public void deleteOwner() {
//       ownerDelete(apiOwner.getId());
//    }
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public ApiOwner ownerCreationTest() {
        double random = Math.random();
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setId(0);
        apiOwner.setFirstName("Tester" + random);
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
                    .as(ApiOwner.class);
    }

    @Test
    public void getOwnerIdTest() {
        //int ownerId = 14;
        RestAssured.given()
                .get("/owners/{id}", apiOwner.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(apiOwner.getId()))
                //.body("name", equalTo(owner.getFirstName()))
                .log().all();
    }
    @Test
    public void ownerDelete(int ownerId){
        RestAssured.given()
                .log().all()
                .delete("/owners/{id}", ownerId)
                .then()
                .statusCode(204);
        }

}
