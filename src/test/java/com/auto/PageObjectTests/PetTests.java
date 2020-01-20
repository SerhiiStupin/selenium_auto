package com.auto.PageObjectTests;

import com.auto.APITests.Owner.ApiTestPreconditions;
import com.auto.TestPreconditions;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Epic("Petclinic")
public class PetTests extends TestPreconditions {

    @Test
    public void pageCheck(){
        goToPetTypesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    @Story("Addding a pet")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("1230")
    public void petTypeAddTest() {
        String name = "Duck";
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.addOwnerAndPets();
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        assertThat(petTypePage.typeList()).isEqualTo(name);
    }
    @Test(description = "Adding an empty Pet")
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
    public void petTypeDeleteTest() {
        String name = "Duck";
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        List<WebElement> before = petTypePage.petsList();
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.addOwnerAndPets();
        goToPetTypesPage();
        List<WebElement> afterAdding = petTypePage.petsList();
        assertThat(before.size()+1).isEqualTo(afterAdding.size());
        petTypePage.deleteLast();
        driver.navigate().refresh();
        List<WebElement> afterDeleting = petTypePage.petsList();
        assertThat(before.size()).isEqualTo(afterDeleting.size());
    }
    @Test
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