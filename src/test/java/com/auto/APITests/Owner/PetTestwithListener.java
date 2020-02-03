package com.auto.APITests.Owner;

import Listener.MyCustomTestListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

@Listeners({MyCustomTestListener.class})
public class PetTestwithListener extends ApiTestPreconditions{
    WebDriver driver;

    Owner owner;
    Pet pet;
    Type type;

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


}
