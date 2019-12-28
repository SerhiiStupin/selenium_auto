package com.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class Veterinarians extends TestPreconditions{

    @Test
    public void pageCheck(){
        goToVetsPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void vetAddTest(){
        goToVetsPage();
        List<WebElement> before = driver.findElements(By.xpath(vetsList));
        goToHomePage();
        navigateToAddVetUsingMenu();
        driver.findElement(By.xpath(firstNameLocator)).sendKeys("Doctor");
        driver.findElement(By.xpath(lastNameLocator)).sendKeys("iBolit");
        driver.findElement(By.xpath("//*[@id='specialties']")).click();
        driver.findElement(By.xpath("//*[@value='2: Object']")).click();
        saveVetButtonClick();
        goToVetsPage();
        List<WebElement> after = driver.findElements(By.xpath(vetsList));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addEmptyVet(){
        goToVetsPage();
        List<WebElement> before = driver.findElements(By.xpath(vetsList));
        navigateToAddVetUsingMenu();
        saveVetButtonClick();
        goToVetsPage();
        List<WebElement> after = driver.findElements(By.xpath(vetsList));
        assertEquals(before.size(), after.size());
    }
    @Test
    public void firstNameValidationTests() {
        String longValidation = "First name must be at least 2 characters long";
        String requiredFirst = "First name is required";
        String firstName = "firstName";
        goToHomePage();
        navigateToAddVetUsingMenu();
        driver.findElement(By.xpath(firstNameLocator)).sendKeys("*");
        helpBlockGetText(firstName);
        validationesAssert(longValidation);

        driver.findElement(By.xpath(firstNameLocator)).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText(firstName);
        validationesAssert(requiredFirst);
    }
    @Test
    public void lastNameValidationTests(){
        String longValidation2 = "Last name must be at least 2 characters long";
        String requiredLast = "Last name is required";
        String lastName = "lastName";
        goToHomePage();
        navigateToAddVetUsingMenu();
        driver.findElement(By.xpath(lastNameLocator)).sendKeys("q");
        helpBlockGetText(lastName);
        validationesAssert(longValidation2);

        driver.findElement(By.xpath(lastNameLocator)).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText(lastName);
        validationesAssert(requiredLast);
    }
    @Test
    public void createVetWithoutType(){
        goToVetsPage();
        List<WebElement> before = driver.findElements(By.xpath(vetsList));
        goToHomePage();
        navigateToAddVetUsingMenu();
        driver.findElement(By.xpath(firstNameLocator)).sendKeys("Doctor");
        driver.findElement(By.xpath(lastNameLocator)).sendKeys("iBolit");
        saveVetButtonClick();
        goToVetsPage();

        isTypeEmpty(before.size()+1);
    }
    @Test
    public void homeButtonTest(){
        goToVetsPage();
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Home']")).click();
        assertUrl(home);
    }
    @Test
    public void backButtonVetsPageTest() {
        goToVetsPage();
        driver.findElement(By.xpath("//tbody/tr[1]/td")).click();
        driver.findElement(By.xpath("//tbody/tr[1]/td[3]/button[text()='Edit Vet']")).click();
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='< Back']")).click();
        assertEquals(vets, driver.getCurrentUrl());
    }

    String firstNameLocator = "//*[@id='firstName']";
    String lastNameLocator = "//*[@id='lastName']";

    public void navigateToAddVetUsingMenu(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][text()='Veterinarians']")).click();
        driver.findElement(By.xpath("//a[@routerlink='/vets/add']")).click();
    }
    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[text()='Save Vet']")).click();
    }

    public void helpBlockGetText(String name){
    driver.findElement(By.xpath("//*[@id='" + name + "']/following-sibling::span[2]")).getText();
}
    public void validationesAssert(String helpBlock){
        String blockText = driver.findElement(By.xpath("//span[@class='help-block']")).getText();
        assertEquals(blockText, helpBlock);
    }
    public void isTypeEmpty(int userId){
        driver.findElement(By.xpath("//tr[" + userId +"]/td[2]")).getText();
    }

}
