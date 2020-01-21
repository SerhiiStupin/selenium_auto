package com.auto.PageObjectTests;

import com.auto.APITests.Owner.ApiTestPreconditions;
import com.auto.TestPreconditions;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Epic("Petclinic")
@Feature("PetTypes")
public class PetTests extends TestPreconditions {

    @Test(description = "Checking Pets page")
    @Story("Checking the URL")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("pettype.com")
    public void pageCheck(){
        goToPetTypesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test(description = "Adding a new Pet")
    @Story("Adding a new pet")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("pettype.com")
    public void petTypeAddTest() {
        String name = "Duck";
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.petTypeadding();
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        assertThat(petTypePage.typeList()).isEqualTo(name);
    }
    @Test(description = "Adding an empty Pet")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and type")
    @TmsLink("pettype.com")
    public void addingEmptyPet(){
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        List<WebElement> before = petTypePage.petsList();
        petTypePage.addBtn();
        petTypePage.saveBtn();
        List<WebElement> after = petTypePage.petsList();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test(description = "Delete the new petType")
    @Severity(SeverityLevel.NORMAL)
    @Story("Deleting of the pettype")
    @TmsLink("pettype.com")
    public void petTypeDeleteTest() {
        String name = "Duck";
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        List<WebElement> before = petTypePage.petsList();
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.petTypeadding();
        goToPetTypesPage();
        List<WebElement> afterAdding = petTypePage.petsList();
        assertThat(before.size()+1).isEqualTo(afterAdding.size());
        petTypePage.deleteLast();
        driver.navigate().refresh();
        List<WebElement> afterDeleting = petTypePage.petsList();
        assertThat(before.size()).isEqualTo(afterDeleting.size());
    }
    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("pettype.com")
    public void homeButtonTest() {
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        petTypePage.homeBtn();
        assertUrl(home);
    }
}
//    public void petTypeAddTest() {
//        String name = "Alligator";
//        goToPetTypesPage();
//        PetTypePage petTypePage = new PetTypePage(driver);
//        petTypePage.addBtn();
//        petTypePage.setName(name);
//        petTypePage.saveBtn();
//        assertThat(petTypePage.typeList()).isEqualTo(name);
//    }
//@Test(description = "Delete the new petType")
//public void petTypeDeleteTest() {
//    String name = "Chupakabra";
//    goToPetTypesPage();
//    PetTypePage petTypePage = new PetTypePage(driver);
//    List<WebElement> before = petTypePage.petsList();
//    petTypePage.addBtn();
//    petTypePage.setName(name);
//    petTypePage.saveBtn();
//    assertThat(petTypePage.typeList()).isEqualTo(name);
//    List<WebElement> afterAdding = petTypePage.petsList();
//    assertThat(before.size()+1).isEqualTo(afterAdding.size());
//    petTypePage.deleteLast();
//    driver.navigate().refresh();
//    List<WebElement> afterDeleting = petTypePage.petsList();
//    assertThat(before.size()).isEqualTo(afterDeleting.size());
//}