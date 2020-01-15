package com.auto.APITests.Delete;

import com.auto.APITests.Owner.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OwnerTests {
    Owner owner;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
    @BeforeMethod
    public void createOwner() {
        owner = createOwnerforUpdate();
    }
    @Test
    public void ownerUpdate(){

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .put("/owners/{id}", owner.getId())
                .then()
                .statusCode(204)
                //.body("id", equalTo(newApiOwner.getId()))
                .log().all();
        //int id = 32;
//        NewApiOwner apiOwner = new NewApiOwner();
//        apiOwner.setId(Integer.valueOf(newApiOwner.getId()));
//        apiOwner.setFirstName("Kozak");
//        apiOwner.setLastName("Mamay");
//        apiOwner.setCity("Odessa");
//        apiOwner.setAddress("Address");
//        apiOwner.setTelephone("0987654321");
//        RestAssured.given()
//                .log().all()
//                .put("/owners/{id}", newApiOwner.getId())
//                .then()
//                .statusCode(204)
////                .body(newApiOwner.getLastName(), equalTo("Mamay"))
////                .body("id", equalTo(Integer.parseInt(newApiOwner.getId())))
//                .log().all();
    }
    private Owner createOwnerforUpdate() {
        Owner apiOwner = new Owner();
        apiOwner.setId(0);
        apiOwner.setFirstName("Test");
        apiOwner.setLastName("User");
        apiOwner.setCity("Dnipro");
        apiOwner.setAddress("Address");
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
//    private void ownerDelete2(String ownerId){
//        RestAssured.given()
//                .log().all()
//                .delete("/owners/{id}", newApiOwner.getId())
//                .then()
//                .statusCode(204);
//    }
//    @Test
//    public void getOwners(){
//        RestAssured.given()
//                .get("/owners")
//                .then()
//                .statusCode(200)
//                .body("id", hasItems(1, 2, 3, 10))
//                .body("firstName", hasItems("Jean", "David"))
//                .body("lastName", hasItems("Estaban", "Black"))
//                .body("address", hasItem("2335 Independence La."))
//                .log().all();
//    }
//    @Test
//    public void createOwner() {
//        NewApiOwner newApiOwner = new NewApiOwner();
//        newApiOwner.setId(11);
//        newApiOwner.setFirstName("Termi");
//        newApiOwner.setLastName("Nator");
//        newApiOwner.setCity("LA");
//        newApiOwner.setAddress("Street");
//        newApiOwner.setTelephone("1234567890");
//        ApiOwner owner = RestAssured.given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .body(newApiOwner)
//                .post("/owners")
//                .then()
//                .statusCode(201)
//                .body("id", notNullValue())
//                .body("firstName", equalTo(newApiOwner.getFirstName()))
//                .log().all()
//                .extract().body()
//                .as(ApiOwner.class);
//
//        assertThat(owner.getId()).isNotEqualTo(0);
//        assertThat(owner.getFirstName()).isEqualTo(newApiOwner.getFirstName());
//        System.out.println(newApiOwner.getId());
//    }
//    @Test
//    public void searchOfOwner(){
//        String lastName = "Nator";
//        ResponseBodyExtractionOptions body = (ResponseBodyExtractionOptions) RestAssured.given()
//                .get("/owners/*/lastname/{lastName}", lastName)
//                .then()
//                .statusCode(200)
//                .body("lastName", hasItem(lastName))
//                .log().all()
//                .extract().body();
//
//        String id = body.jsonPath().getString("id");
//        String name = body.jsonPath().getString("name");
//    }
}
