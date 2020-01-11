package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

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
        newOwnerPage.clickAddOwnerButton();
        WebElement newOwner = ownersPage.addedOwnerSearch();
        assertTrue(newOwner.isDisplayed());
        newOwner.click();
        OwnerInformationPage ownerInformationPage = new OwnerInformationPage(driver);
        PetsAndVisitPageComponent petsAndVisitPageComponent = ownerInformationPage.addNewPetBtn();
        assertEquals(title, petsAndVisitPageComponent.pageTitle());
        petsAndVisitPageComponent.nameField(petName);
        petsAndVisitPageComponent.calendarClick();
        petsAndVisitPageComponent.nextMonthArrowClick();
        petsAndVisitPageComponent.nextMonthArrowClick();
        boolean isCorrectDate = waiting().until
                (ExpectedConditions.attributeToBe(petsAndVisitPageComponent.birthDate() , "value", "2020/01/31"));
        assertTrue(isCorrectDate, "The date is incorrect");
        petsAndVisitPageComponent.typeSelect();
        petsAndVisitPageComponent.dropDownOpen();
        petsAndVisitPageComponent.submitBtn();
        assertEquals(petName, petsAndVisitPageComponent.nameCheck());
        goToOwnersPage();

        assertEquals(petName, petsAndVisitPageComponent.petAddingCheck());





        driver.findElement(By.xpath(nameIdLocator)).sendKeys(petName);
        driver.findElement(By.xpath("//*[@class='mat-datepicker-toggle-default-icon']")).click();
        driver.findElement(By.xpath("//*[@class='mat-calendar-next-button mat-icon-button']")).click();
        driver.findElement(By.xpath("//*[@class='mat-calendar-body-cell-content'][text()='31']")).click();
        WebElement birthDate = driver.findElement(By.xpath("//*[@name='birthDate']"));
        boolean isCorrectDate = waiting().until(ExpectedConditions.attributeToBe(birthDate, "value", "2020/01/31"));
        assertTrue(isCorrectDate, "The date is incorrect");
        driver.findElement(By.xpath("//*[@id='type']")).click();
        driver.findElement(By.xpath("//div/select/option[last()]")).click();
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        String nameCheck = driver.findElement(By.xpath("//dl/dd[1]")).getText();
        assertEquals(petName, nameCheck);
        goToOwnersPage();
        waiting().until(ExpectedConditions.textMatches(By.xpath("//h2"), Pattern.compile("Owners")));
        String petAddingCheck = driver.findElement(By.xpath("//tr[11]/td[last()]/tr[1]")).getText();
        assertEquals(petName, petAddingCheck);
    }
}
