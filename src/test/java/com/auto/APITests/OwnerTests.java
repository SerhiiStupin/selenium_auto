package com.auto.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class OwnerTests {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
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
    public void createOwner() {
        NewApiOwner newApiOwner = new NewApiOwner();
        newApiOwner.setId(0);
        newApiOwner.setFirstName("Termi");
        newApiOwner.setLastName("Nator");
        newApiOwner.setCity("LA");
        newApiOwner.setAddress("Street");
        newApiOwner.setTelephone("1234567890");
        ApiOwner owner = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(newApiOwner)
                .post("/owners")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(newApiOwner.getFirstName()))
                .log().all()
                .extract().body()
                .as(ApiOwner.class);

        assertThat(owner.getId()).isNotEqualTo(0);
        assertThat(owner.getFirstName()).isEqualTo(newApiOwner.getFirstName());
        System.out.println(newApiOwner.getId());
    }
}
