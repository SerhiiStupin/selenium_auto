package com.auto.PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewVeterPage {
    private WebDriver driver;

    public NewVeterPage(WebDriver driver) {
        this.driver = driver;
    }
    String firstNameLongValidation = "First name must be at least 2 characters long";
    String requiredFirst = "First name is required";
    String firstName = "firstName";
    String lastNamelongValidation = "Last name must be at least 2 characters long";
    String requiredLast = "Last name is required";
    String lastName = "lastName";
    //WebElement backButton = (WebElement) By.xpath("//*[@class='btn btn-default'][text()='< Back']");

    public void createVet(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        specTypeList(veterinarian.getType());
    }

    public void setFirstName(String firstName){
        WebElement nameField = firstNameLocator();
        nameField.clear();
        nameField.sendKeys(firstName);
    }
    public void clearFirstName(){
        WebElement nameField = firstNameLocator();
        nameField.sendKeys(Keys.BACK_SPACE);
    }
    public void setLastName(String lastName){
        WebElement lastNameField = lastNameLocator();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void clearLastName(){
        WebElement nameField = lastNameLocator();
        nameField.sendKeys(Keys.BACK_SPACE);
    }
    public void specTypeList(int itemSelect){
        WebElement specList = driver.findElement(By.xpath("//*[@id='specialties']"));
        specList.click();
        WebElement selectItem = driver.findElement(By.xpath("//*[@value='"+itemSelect+": Object']"));
        selectItem.click();
    }
    public VeterinariansPage saveVetButtonClick(){
        WebElement saveVetButton = driver.findElement(By.xpath("//*[text()='Save Vet']"));
        saveVetButton.click();
        return new VeterinariansPage(driver);
    }
    public String helpBlockGetText(String name){
        WebElement actualError = driver.findElement(By.xpath("//*[@id='" + name + "']/following-sibling::span[2]"));
        actualError.getText();
        return actualError.getText();
    }
    private WebElement firstNameLocator(){
        return driver.findElement(By.xpath("//*[@id='firstName']"));
    }
    private WebElement lastNameLocator(){
        return driver.findElement(By.xpath("//*[@id='lastName']"));
    }
}
