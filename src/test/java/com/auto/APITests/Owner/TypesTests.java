package com.auto.APITests.Owner;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@Epic("PetClinic")
@Feature("Pettypes API")
public class TypesTests extends ApiTestPreconditions{
    private Type type;

    @BeforeMethod
    @Step("PetType creating")
    public void createPetType() {
        type = postPetTypeTestWithObject();
    }

    @AfterClass
    @Step("PetType deleting")
    public void deletePetType() {
        deletePetTypeByIdTest(type.getId());
    }

    @Test(description = "Update of the pettype")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update of the pettype")
    @TmsLink("pettypes.com")
    @Issue("Bug-30")
    public void petTypeUpdate() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .put(petTypedIdUrl, type.getId())
                .then()
                .statusCode(204)
                .log().all();
    }
    @Test(description = "Update of the pettype. Incorrect Pettype")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Update of the pettype. Incorrect Pettype")
    @TmsLink("pettypes.com")
    @Issue("Bug-31")
    public void petTypeUpdateError() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .put(petTypedIdUrl, 0)
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test(description = "Get pettypes by id and typename")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Get pettypes by id and typename")
    @TmsLink("pettypes.com")
    @Issue("Bug-32")
    public void getPetTypes(){
        given()
                .get(petTypesUrl)
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 3, 6))
                .body("name", hasItems("cat", "hamster", "snake"))
                .log().all();
    }
    @Test(description = "Bad request error")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Bad request error")
    @TmsLink("pettypes.com")
    @Issue("Bug-33")
    public void badRequest(){
        given()
                .then()
                .statusCode(400)
                .body("lastName", hasItems(0))
                .log().all();
    }
    @Test(description = "Created pettype getting")
    @Severity(SeverityLevel.NORMAL)
    @Story("Created pettype getting")
    @TmsLink("pettypes.com")
    @Issue("Bug-34")
    public void getPetTypeByIdTest() {
        RestAssured.given()
                .get(petTypedIdUrl, type.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(type.getId()))
                .body("name", equalTo(type.getName()))
                .log().all();
    }
    @Test(description = "Created pettype getting. Error")
    @Severity(SeverityLevel.NORMAL)
    @Story("Created pettype getting. Error")
    @TmsLink("pettypes.com")
    @Issue("Bug-35")
    public void getPetTypeByIdTest404() {
        RestAssured.given()
                .get(petTypedIdUrl, 0)
                .then()
                .statusCode(404)
                .log().all();
    }
    @Test(description = "Creating pettype. Incorrect body.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Creating pettype. Incorrect body.")
    @TmsLink("pettypes.com")
    @Issue("Bug-36")
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
    @Test(description = "Pettype creating")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Pettype creating")
    @TmsLink("pettypes.com")
    @Issue("Bug-37")
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
    @Test(description = "Incorrect pettype deleting")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Incorrect pettype deleting")
    @TmsLink("pettypes.com")
    @Issue("Bug-38")
    public void deletePetTypeByIdTestError() {
        int petId = 651;
        given()
                .log().all()
                .delete(petTypedIdUrl, petId)
                .then()
                .statusCode(404);
    }
    @Test(description = "Adding of pettype test")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Adding of pettype test")
    @TmsLink("pettypes.com")
    @Issue("Bug-39")
    public void petTypeAdd(){
        type = new Type();
        type.setName("alligator");
        type =  RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .post(petTypesUrl)
                .then()
                .log().all()
                .statusCode(201)
                .extract().body()
                .as(Type.class);
    }
    @Test(description = "Error while dding of pettype")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Error while dding of pettype")
    @TmsLink("pettypes.com")
    @Issue("Bug-40")
    public void petTypeAddError(){
        type = new Type();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .post(petTypesUrl)
                .then()
                .log().all()
                .statusCode(400);
    }
}
