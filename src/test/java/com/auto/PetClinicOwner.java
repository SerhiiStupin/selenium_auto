package com.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;


public class PetClinicOwner extends TestPreconditions{


    @Test
    public void pageCheck(){
        goToHomePage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void addNewOwnerTest() {
        String firstName = "James";
        String lastName = "Bond";
        goToOwnersPage();
        List<WebElement> before = driver.findElements(By.cssSelector(ownersList));
        goToHomePage();
        goToAddOwnerPageUsingMenu();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.id(firstNameId)).sendKeys(firstName);
        driver.findElement(By.id(lastNameId)).sendKeys(lastName);
        driver.findElement(By.id(addressId)).sendKeys("Some Street");
        driver.findElement(By.id("city")).sendKeys("London");
        driver.findElement(By.id(telephoneId)).sendKeys("2151212");
        addOwnerButtonClick();
        List<WebElement> after = driver.findElements(By.cssSelector(ownersList));
        assertEquals(before.size()+1, after.size());
    }

    @Test
    public void backButtonTest() {
        goToHomePage();
        goToAddOwnerPageUsingMenu();
        assertUrl(driver.getCurrentUrl());
        backButtonClick();
        assertEquals(owners, driver.getCurrentUrl());
    }

    @Test
    public void backButtonFromOwnerTest() {
        goToOwnersPage();
        driver.findElement(By.partialLinkText("James Bond")).click();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        assertEquals(owners, driver.getCurrentUrl());
    }

    @Test
    public void firstNameValidationTests() {
        String firstName = "First name must be at least 2 characters long";
        String required = "First name is required";
        goToOwnersPage();
        addOwnerButtonClick();
        driver.findElement(By.id(firstNameId)).sendKeys("1");
        helpBlockGetText();
        validationsAssert(firstName);

        driver.findElement(By.id(firstNameId)).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationsAssert(required);
    }

    @Test
    public void lastNameValidationTests() {
        String lastName = "Last name must be at least 2 characters long";
        String required = "Last name is required";
        goToOwnersPage();
        addOwnerButtonClick();
        driver.findElement(By.id(lastNameId)).sendKeys("q");
        helpBlockGetText();
        validationsAssert(lastName);

        driver.findElement(By.id(lastNameId)).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationsAssert(required);
    }

    @Test
    public void addressValidationTest() {
        String address = "Address is required";
        goToOwnersPage();
        addOwnerButtonClick();
        driver.findElement(By.id(addressId)).sendKeys("-");
        driver.findElement(By.id(addressId)).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationsAssert(address);
    }

    @Test
    public void telephoneTest() {
        String phoneError = "Phone number only accept digits";
        String required = "Phone number is required";
        goToOwnersPage();
        addOwnerButtonClick();
        driver.findElement(By.id(telephoneId)).sendKeys("q");
        helpBlockGetText();
        validationsAssert(phoneError);

        driver.findElement(By.id(telephoneId)).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationsAssert(required);
    }

    public void goToAddOwnerPageUsingMenu() {
        WebElement menuOwnerDropdown = driver.findElement(By.className("dropdown-toggle"));
        menuOwnerDropdown.click();
        WebElement addNew = driver.findElement(By.cssSelector("a[href='/petclinic/owners/add']"));
        addNew.click();
    }
    String firstNameId = "firstName";
    String lastNameId = "lastName";
    String addressId = "address";
    String telephoneId = "telephone";

    public void addOwnerButtonClick(){
        driver.findElement(By.xpath("//*[text()='Add Owner']")).click();
    }
    public void backButtonClick(){
        driver.findElement(By.xpath("//*[text()='Back']")).click();
    }
    public void helpBlockGetText(){
        driver.findElement(By.xpath("//span[@class='help-block']")).getText();
    }
    public void validationsAssert(String helpBlock){
        String blockText = driver.findElement(By.xpath("//span[@class='help-block']")).getText();
        assertEquals(blockText, helpBlock);
    }
}
