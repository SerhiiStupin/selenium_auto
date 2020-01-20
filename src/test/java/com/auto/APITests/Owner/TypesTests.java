package com.auto.APITests.Owner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TypesTests extends ApiTestPreconditions{
    String petTypesUrl = "/pettypes";
    String petTypedIdUrl = "/pettypes/{id}";
    private Type type;

    @BeforeMethod
    public void createPetType() {
        type = postPetTypeTestWithObject();
    }

    @AfterMethod
    public void deletePetType() {
        deletePetTypeByIdTest(type.getId());
    }
    @Test
    public void petTypeUpdate() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .put(petTypedIdUrl, type.getId())
                .then()
                .statusCode(204)
                .log().all();
    }
    @Test
    public void petTypeUpdateError() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .put(petTypedIdUrl, 0)
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test
    public void getPetTypes(){
        given()
                .get(petTypesUrl)
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 6))
                .body("name", hasItems("cat", "hamster", "snake"))
                .log().all();
    }
    @Test
    public void badRequest(){
        given()
                //.get("/pettypes")
                .then()
                .statusCode(400)
                .body("lastName", hasItems(0))
                .log().all();
    }
    @Test
    public void getPetTypeByIdTest() {
        RestAssured.given()
                .get(petTypedIdUrl, type.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(type.getId()))
                .body("name", equalTo(type.getName()))
                .log().all();
    }
    @Test
    public void getPetTypeByIdTest404() {
        RestAssured.given()
                .get(petTypedIdUrl, 0)
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test
    public void postPetTypeTestWithObjectError() {
        Type bizon = new Type();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bizon)
                .post(petTypesUrl)
                .then()
                .statusCode(400)
                .log().all();
    }
    private Type postPetTypeTestWithObject() {
        Type bizon = new Type();
        bizon.setName("bizzone");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bizon)
                .post(petTypesUrl)
                .then()
                .statusCode(201)
                .extract().body()
                .as((java.lang.reflect.Type) Type.class);
    }

    public void deletePetTypeByIdTest(int petId) {
        given()
                .log().all()
                .delete(petTypedIdUrl, petId)
                .then()
                .statusCode(204);
    }
    @Test
    public void deletePetTypeByIdTestError() {
        int petId = 651;
        given()
                .log().all()
                .delete(petTypedIdUrl, petId)
                .then()
                .statusCode(404);
    }
}
