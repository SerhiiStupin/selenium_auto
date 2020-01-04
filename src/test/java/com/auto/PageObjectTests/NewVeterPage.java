package com.auto.PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewVeterPage {
    private WebDriver driver;

    public NewVeterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void createVet(Veterinarian veterinarian) {
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        specTypeList(veterinarian.getType());
    }

    public void setFirstName(String firstName){
        WebElement nameField = driver.findElement(By.xpath("//*[@id='firstName']"));
        nameField.clear();
        nameField.sendKeys(firstName);
    }
    public void setLastName(String lastName){
        WebElement lastNameField = driver.findElement(By.xpath("//*[@id='lastName']"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
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

    String firstNameLongValidation = "First name must be at least 2 characters long";
    //String requiredFirst = "First name is required";
    String firstName = "firstName";
    String lastNamelongValidation = "Last name must be at least 2 characters long";
    //String requiredLast = "Last name is required";
    String lastName = "lastName";
//    WebElement backButton = (WebElement) By.xpath("//*[@class='btn btn-default'][text()='< Back']");
}
