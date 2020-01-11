package com.auto.PageObjectTests;

import com.sun.xml.internal.org.jvnet.staxex.Base64Data;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewOwnerPage {
    String firstNameLongValidation = "First name must be at least 2 characters long";
    String requiredFirst = "First name is required";
    String firstNameLocator = "firstName";
    String lastNamelongValidation = "Last name must be at least 2 characters long";
    String requiredLast = "Last name is required";
    String lastNameLocator = "lastName";
    String address = "Address is required";
    String addressLocator = "address";
    String city = "City is required";
    String cityLocator = "city";
    String telephone ="Phone number only accept digits";
    String telephoneRequired = "Phone number is required";
    String telephoneLocator = "telephone";

    private WebDriver driver;

    public NewOwnerPage(WebDriver driver) {
        this.driver = driver;
    }

//    public void fillOwner(Owner owner) {
//        setFirstName(owner.getFirstName());
//        setLastName(owner.getLastName());
//        setAddress(owner.getAddress());
//        setCity(owner.getCity());
//        setTelephone(owner.getTelephone());
//    }

    public void setFirstName(String firstName) {
        WebElement name = driver.findElement(By.id(firstNameLocator));
        name.clear();
        name.sendKeys(firstName);
    }
    public void clearFName(){
        WebElement name = driver.findElement(By.id(firstNameLocator));
        name.sendKeys(Keys.BACK_SPACE);
    }
    public void setLastName(String lastName) {
        WebElement lastNameField = driver.findElement(By.id(lastNameLocator));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void clearLastName() {
        WebElement lastNameField = driver.findElement(By.id(lastNameLocator));
        lastNameField.sendKeys(Keys.BACK_SPACE);
    }

    public void setAddress(String address) {
        WebElement addressField = driver.findElement(By.id(addressLocator));
        addressField.clear();
        addressField.sendKeys(address);
    }
    public void clearAddress() {
        WebElement addressField = driver.findElement(By.id(addressLocator));
        addressField.sendKeys(Keys.BACK_SPACE);
    }

    public void setCity(String city) {
        WebElement cityField = driver.findElement(By.id(cityLocator));
        cityField.clear();
        cityField.sendKeys(city);
    }
    public void clearCity() {
        WebElement cityField = driver.findElement(By.id(cityLocator));
        cityField.sendKeys(Keys.BACK_SPACE);
    }
    public void setTelephone(String telephone) {
        WebElement telephoneField = driver.findElement(By.id(telephoneLocator));
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
    }
    public void clearTelephone() {
        WebElement telephoneField = driver.findElement(By.id(telephoneLocator));
        telephoneField.sendKeys(Keys.BACK_SPACE);
    }

    public OwnersPage clickAddOwnerButton() {
        WebElement addOwnerBtn = driver.findElement(By.xpath("//*[text()='Add Owner']"));
        addOwnerBtn.click();
        return new OwnersPage(driver);
    }
    public OwnersPage clickBackButton() {
        WebElement backBtn = driver.findElement(By.xpath("//*[text()='Back']"));
        backBtn.click();
        return new OwnersPage(driver);
    }
    public Object helpBlock(){
        WebElement help = driver.findElement(By.xpath("//*[@class='help-block']"));
        //help.getText();
        return help.getText();
    }
}
