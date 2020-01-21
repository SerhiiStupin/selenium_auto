package com.auto.APITests.Owner;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@Epic("PetClinic")
@Feature("Pets API")
public class PetsTests extends ApiTestPreconditions{
    Owner owner;
    Type type;
    Pet pet;

    @AfterClass
    @Step("Deleting data after test")
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
    @Test(description = "Creating owner with pet and type")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Creating owner with pet and type")
    @TmsLink("pets.com")
    @Issue("Bug-20")
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
    @Test(description = "Get pet by id and lastname")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get pets")
    @TmsLink("pets.com")
    @Issue("Bug-21")
    public void petSearch(){
        RestAssured.given()
                .get(petsUrl)
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 4))
                .body("name", hasItems("Jewel", "Max"))
                .log().all();
    }
    @Test(description = "Get incorrect prt")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Get incorrect pet")
    @TmsLink("pets.com")
    @Issue("Bug-22")
    public void petSearch404(){
        RestAssured.given()
                .get("/pets1")
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test(description = "Getting of the new created pet")
    @Severity(SeverityLevel.NORMAL)
    @Story("Getting of the new created pet")
    @TmsLink("pets.com")
    @Issue("Bug-23")
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
    @Test(description = "Getting of the new with incorrect Id")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Getting of the new with incorrect Id")
    @TmsLink("pets.com")
    @Issue("Bug-25")
    public void petSearchByIdError(){
        int petId = 1011;
        RestAssured.given()
                .get(petsIdUrl, petId)
                .then()
                .statusCode(404)
                .log().all();
    }
}
//    public void PetTypeCheck(){
//        RestAssured.given()
//                .get(petsUrl + "/pettypes")
//                .then()
//                .statusCode(200)
//                .body("id", hasItems(1, 5, 6))
//                .body("name", hasItems("hamster", "snake", "lizard"))
//                .log().all();
//    }
//    @Test
//    public void PetTypeCheckError(){
//        RestAssured.given()
//                .then()
//                .statusCode(400)
//                .body("id", hasItems(1, 5, 6))
//                .body("name", hasItems("hamster", "snake", "lizard"))
//                .log().all();
//    }