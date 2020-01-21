package com.auto.PageObjectTests;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewVeterPage {
    private WebDriver driver;

    public NewVeterPage(WebDriver driver) {
        this.driver = driver;
    }
    //WebElement backButton = (WebElement) By.xpath("//*[@class='btn btn-default'][text()='< Back']");

    @Step("Setting vet's data")
    public void createVet(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        specTypeList(veterinarian.getType());
    }
    @Step("Setting first name")
    public void setFirstName(String firstName){
        WebElement nameField = firstNameLocator();
        nameField.clear();
        nameField.sendKeys(firstName);
    }
    @Step("Deleting one letter")
    public void clearFirstName(){
        WebElement nameField = firstNameLocator();
        nameField.sendKeys(Keys.BACK_SPACE);
    }
    @Step("Entering of the last name")
    public void setLastName(String lastName){
        WebElement lastNameField = lastNameLocator();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    @Step("Deleting one letter")
    public void clearLastName(){
        WebElement nameField = lastNameLocator();
        nameField.sendKeys(Keys.BACK_SPACE);
    }
    @Step("Getting one of the vet specialties")
    public void specTypeList(int itemSelect){
        WebElement specList = driver.findElement(By.xpath("//*[@id='specialties']"));
        specList.click();
        WebElement selectItem = driver.findElement(By.xpath("//*[@value='"+itemSelect+": Object']"));
        selectItem.click();
    }
    @Step("Save button click")
    public VeterinariansPage saveVetButtonClick(){
        WebElement saveVetButton = driver.findElement(By.xpath("//*[text()='Save Vet']"));
        saveVetButton.click();
        return new VeterinariansPage(driver);
    }
    @Step("Getting validation error text")
    public String helpBlockGetText(String name){
        WebElement actualError = driver.findElement(By.xpath("//*[@id='" + name + "']/following-sibling::span[2]"));
        actualError.getText();
        return actualError.getText();
    }
    @Step("Getting first name")
    private WebElement firstNameLocator(){
        return driver.findElement(By.xpath("//*[@id='firstName']"));
    }
    @Step("Getting last name")
    private WebElement lastNameLocator(){
        return driver.findElement(By.xpath("//*[@id='lastName']"));
    }
}
