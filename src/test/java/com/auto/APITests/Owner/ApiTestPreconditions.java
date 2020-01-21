package com.auto.APITests.Owner;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTestPreconditions {
    String lastName = "Nator";
    String owners = "/owners";
    String petTypesUrl = "/pettypes";
    String petTypedIdUrl = "/pettypes/{id}";
    String specUrl = "/specialties";
    String specIdUrl = "/specialties/{id}";
    String vetLastName = "Bolit";
    String vetsUrl = "/vets";
    String vetsIdUrl = "/vets/{id}";
    String petsUrl = "/pets";
    String petsIdUrl = "/pets/{id}";

    Owner owner;
    Pet pet;
    Type type;
    Specialty specialty;
    Vets vets;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        //RestAssured.baseURI = "http://139.59.149.247";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
        RestAssured.defaultParser = Parser.JSON;
    }
    @Step("Entering new Spec data and save")
        public void specCreationPrec() {
        specialty = new Specialty();
        specialty.setName("lor");
        specialty = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialty)
                .post("/specialties")
                .then()
                .statusCode(201)
                .extract().body()
                .as(Specialty.class);
    }
    @Step("Entering new Vets data and save")
    public void vetCreationPrec() {
        vets = new Vets();
        List<Specialty> spec = new ArrayList<>();
        vets.setFirstName("i");
        vets.setLastName("Bolit");
        vets.setSpecialties(spec);
        vets =  RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vets)
                .post("/vets")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo(vets.getFirstName()))
                .extract().body()
                .as(Vets.class);
    }
    @Step("Entering new owner data and save")
    public void addOwner() {
        owner = new Owner();
        owner.setFirstName("Pavlo");
        owner.setLastName("Zibrov");
        owner.setAddress("Khreschatik");
        owner.setCity("Kyiv");
        owner.setTelephone("2589631470");
        owner = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/owners")
                .then()
                .statusCode(201)
                .extract().body()
                .as(Owner.class);
    }
    @Step("Entering new PetType data and save")
    public void petTypeadding(){
        type = new Type();
        type.setName("Duck");
        type = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .post("/pettypes")
                .then()
                .statusCode(201)
                .extract().body()
                .as(Type.class);
    }
    @Step("Entering new Owner with Pet and PetType data + save")
    public void addOwnerAndPets(){
        owner = new Owner();
        owner.setFirstName("Pavlo");
        owner.setLastName("Zibrov");
        owner.setAddress("Khreschatik");
        owner.setCity("Kyiv");
        owner.setTelephone("2589631470");
        owner = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/owners")
                .then()
                .statusCode(201)
                .extract().body()
                .as(Owner.class);

        pet = new Pet();
        pet.setName("Villy");
        pet.setType(type);
        pet.setBirthDate("2020/01/18");
        pet.setOwner(owner);
        pet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .post("/pets")
                .then()
                .statusCode(201)
                .extract().body()
                .as(Pet.class);

        pet.setBirthDate("2020/10/10");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .put("/pets/"+ pet.getId())
                .then()
                .statusCode(204)
                .log().all();
    }
}
