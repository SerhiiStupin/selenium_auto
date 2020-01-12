package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetToOwner extends TestPreconditions {
    @Test
    public void addPetToOwner(){
        String firstName = "John";
        String lastName = "Snow";
        String petName = "Nymeria";
        String title = "Add Pet";
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setFirstName(firstName);
        newOwnerPage.setLastName(lastName);
        newOwnerPage.setAddress("The Wall");
        newOwnerPage.setCity("Winterfell");
        newOwnerPage.setTelephone("2151212");
        ownersPage = newOwnerPage.clickAddOwnerButton();
        goToOwnersPage();
        WebElement newOwner = ownersPage.addedOwnerSearch();
        assertTrue(newOwner.isDisplayed());
        newOwner.click();
        OwnerInformationPage ownerInformationPage = new OwnerInformationPage(driver);
        PetsAndVisitPageComponent petsAndVisitPageComponent = ownerInformationPage.addNewPetBtn();
        assertEquals(title, petsAndVisitPageComponent.pageTitle());
        petsAndVisitPageComponent.nameField(petName);
        petsAndVisitPageComponent.calendarClick();
        petsAndVisitPageComponent.nextMonthArrowClick();
        petsAndVisitPageComponent.dateSelect();
//        boolean isCorrectDate = waiting().until
//                (ExpectedConditions.attributeToBe(petsAndVisitPageComponent.birthDate(), "value", "2020/01/31"));
//        assertTrue(isCorrectDate, "The date is incorrect");
        petsAndVisitPageComponent.typeSelect();
        petsAndVisitPageComponent.dropDownOpen();
        petsAndVisitPageComponent.submitBtn();
        goToOwnersPage();
//        assertEquals(petName, petsAndVisitPageComponent.nameCheck());
//        goToOwnersPage();
        assertEquals(petName, petsAndVisitPageComponent.petAddingCheck());


    }
}
