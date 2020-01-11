package com.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPetsToOwner extends TestPreconditions{
//    public AddPetsToOwner(WebDriver driver) {
//        super(driver);
//    }

    @Test
    public void addPetNewType(){
        String name = "Wolf";
        goToPetTypesPage();
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        petTypeAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(name);
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addPetToOwner(){
        goToOwnersPage();
        String firstName = "John";
        String lastName = "Snow";
        String petName = "Nymeria";
        goToOwnersPage();
        addOwnerButtonClick();
        driver.findElement(By.xpath(nameID)).clear();
        driver.findElement(By.xpath(nameID)).sendKeys(firstName);
        driver.findElement(By.xpath(lastNameId)).clear();
        driver.findElement(By.xpath(lastNameId)).sendKeys(lastName);
        driver.findElement(By.xpath(address)).clear();
        driver.findElement(By.xpath(address)).sendKeys("The Wall");
        driver.findElement(By.xpath(city)).clear();
        driver.findElement(By.xpath(city)).sendKeys("Winterfell");
        driver.findElement(By.xpath(telephone)).clear();
        driver.findElement(By.xpath(telephone)).sendKeys("2151212");
        addOwnerButtonClick();
        goToOwnersPage();
        waiting().until(ExpectedConditions.textMatches(By.xpath("//h2"), Pattern.compile("Owners")));
        WebElement newOwner = driver.findElement(By.xpath("//*[text()='John Snow']"));
        assertTrue(newOwner.isDisplayed());
        newOwner.click();
        driver.findElement(By.xpath("//*[text()='Add New Pet']")).click();
        String pageTitle = driver.findElement(By.xpath("//h2")).getText();
        assertEquals(title, pageTitle);
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

    String title = "Add Pet";
    String nameIdLocator = "//*[@id='name']";
    String nameID = "//*[@id='firstName']";
    String lastNameId = "//*[@id='lastName']";
    String address = "//*[@id='address']";
    String city = "//*[@id='city']";
    String telephone = "//*[@id='telephone']";

    public void addOwnerButtonClick(){
        driver.findElement(By.xpath("//*[text()='Add Owner']")).click();
    }
    public void petTypeAddButton(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()=' Add ']")).click();
    }
    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Save']")).click();
    }

}
