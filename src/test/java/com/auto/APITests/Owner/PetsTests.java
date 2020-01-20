package com.auto.APITests.Owner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PetsTests extends ApiTestPreconditions{
    String petsUrl = "/pets";
    String petsIdUrl = "/pets/{id}";
    Owner owner;
    Type type;
    Pet pet;

    @AfterClass
    public void cleanData() {
        if (pet != null){
            RestAssured.given()
                    .log().all()
                    .delete(petsIdUrl, pet.getId())
                    .then()
                    .statusCode(204);

        }
        if (owner != null) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .delete("/owners/" + owner.getId())
                    .then()
                    .statusCode(204);
        }

        if (type != null) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .delete("/pettypes/" + type.getId())
                    .then()
                    .statusCode(204);
        }
    }
    @Test
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

        pet = new Pet();
        pet.setName("Villy");
        pet.setType(type);
        pet.setBirthDate("2020/01/18");
        pet.setOwner(owner);
        pet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .post(petsUrl)
                .then()
                .statusCode(201)
                .extract().body()
                .as(Pet.class);

        pet.setBirthDate("2020/10/10");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .put(petsIdUrl, pet.getId())
                .then()
                .statusCode(204)
                .log().all();
    }
    @Test
    public void petSearch(){
        RestAssured.given()
                .get(petsUrl)
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 4))
                .body("name", hasItems("Jewel", "Max"))
                .log().all();
    }
    @Test
    public void petSearch404(){
        RestAssured.given()
                .get("/pets1")
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test
    public void PetTypeCheck(){
        RestAssured.given()
                .get(petsUrl + "/pettypes")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 5, 6))
                .body("name", hasItems("hamster", "snake", "lizard"))
                .log().all();
    }
    @Test
    public void PetTypeCheckError(){
        RestAssured.given()
                .then()
                .statusCode(400)
                .body("id", hasItems(1, 5, 6))
                .body("name", hasItems("hamster", "snake", "lizard"))
                .log().all();
    }
    @Test
    public void petSearchById(){
        int petId = 1;
        RestAssured.given()
                .get(petsIdUrl, petId)
                .then()
                .statusCode(200)
                .body("birthDate", equalTo("2010/09/07"))
                .body("name", equalTo("Leo"))
                .log().all();
    }
    @Test
    public void petSearchByIdError(){
        int petId = 1011;
        RestAssured.given()
                .get(petsIdUrl, petId)
                .then()
                .statusCode(404)
                .log().all();
    }
}