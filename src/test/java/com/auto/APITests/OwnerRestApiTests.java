package com.auto.APITests;

import com.auto.PageObjectTests.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class OwnerRestApiTests {
    private Owner owner;

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
    public void createOwner(){
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setId(0);
        apiOwner.setFirstName("Tester");
        apiOwner.setLastName("User");
        apiOwner.setCity("LA");
        apiOwner.setAddress("Street");
        apiOwner.setTelephone("1234567890");
        ApiOwner owner = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(apiOwner)
                .post("/owners")
                .then()
                .statusCode(201)
                //.body("id", notNullValue())
                .body("firstName", equalTo(apiOwner.getFirstName()))
                .log().all()
                .extract().body()
                .as(ApiOwner.class);
    }
    @Test
    public void postOwnerTest() {
        String name = "Test";
        String last = "User";
        ResponseBodyExtractionOptions body = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"address\": \"street\",\n" +
                        "  \"city\": \"NY\",\n" +
                        "  \"firstName\": \""+name+"\",\n" +
                        "  \"id\": 0,\n" +
                        "  \"lastName\": \""+last+"\",\n" +

                        "  \"telephone\": \"1234567890\"\n" +
                        "}}")
                .post("/owners")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(name))
                .extract().body();
    }
    @Test
    public void addPet(){
        ApiPet apiPet = new ApiPet();
        ApiOwner owner = new ApiOwner();
        apiPet.setName("QWERTY");
        apiPet.setBirthDate("2010/10/10");
        apiPet.setId(1);
        Type type = new Type();
        type.setId(5);
        type.setName("Alligator");
        apiPet.setType(type);

    }
}
