package com.auto.APITests.Delete;

import com.auto.APITests.Owner.NewApiOwner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteQWERTYUIO {
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
