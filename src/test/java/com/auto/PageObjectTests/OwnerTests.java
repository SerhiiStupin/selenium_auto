package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerTests extends TestPreconditions {

    @Test
    public void pageCheck(){
        goToOwnersPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void addNewOwnerTest() {
        String firstName = "James";
        String lastName = "Bond";
        String address = "Some Street";
        String city = "London";
        String phone = "2151212";
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        List<WebElement> before = ownersPage.ownersList();
        assertUrl(driver.getCurrentUrl());
        ownersPage.clickAddOwnerBtn();
        NewOwnerPage newOwnerPage = new NewOwnerPage(driver);
        newOwnerPage.setFirstName(firstName);
        newOwnerPage.setLastName(lastName);
        newOwnerPage.setAddress(address);
        newOwnerPage.setCity(city);
        newOwnerPage.setTelephone(phone);
        newOwnerPage.clickAddOwnerButton();
        List<WebElement> after = ownersPage.ownersList();
        assertThat(before.size()+1).isEqualTo(after.size());
    }
    @Test
    public void backButtonTest() {
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        assertUrl(driver.getCurrentUrl());
        newOwnerPage.clickBackButton();
        assertThat(owners).isEqualTo(driver.getCurrentUrl());
    }
    @Test
    public void firstNameValidationTests() {
        String firstNameLongValidation = "First name must be at least 2 characters long";
        String requiredFirst = "First name is required";
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setFirstName("w");
        assertThat(firstNameLongValidation).isEqualTo(newOwnerPage.helpBlock());

        newOwnerPage.clearFName();
        assertThat(requiredFirst).isEqualTo(newOwnerPage.helpBlock());
    }
    @Test
    public void lastNameValidationTests() {
        String lastNamelongValidation = "Last name must be at least 2 characters long";
        String requiredLast = "Last name is required";
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setLastName("p");
        assertThat(lastNamelongValidation).isEqualTo(newOwnerPage.helpBlock());

        newOwnerPage.clearLastName();
        assertThat(requiredLast).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test
    public void addressValidationTest() {
        String address = "Address is required";

        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setAddress("*");
        newOwnerPage.clearAddress();
        assertThat(address).isEqualTo(newOwnerPage.helpBlock());
    }
    @Test
    public void cityValidationTest() {
        String city = "City is required";
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setCity("-");
        newOwnerPage.clearCity();
        assertThat(city).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test
    public void telephoneTest() {
        String telephone ="Phone number only accept digits";
        String telephoneRequired = "Phone number is required";
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setTelephone(" ");
        assertThat(telephone).isEqualTo(newOwnerPage.helpBlock());

        newOwnerPage.clearTelephone();
        assertThat(telephoneRequired).isEqualTo(newOwnerPage.helpBlock());
    }

}
