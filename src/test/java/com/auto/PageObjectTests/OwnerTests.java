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
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setFirstName("w");
        assertThat(newOwnerPage.firstNameLongValidation).isEqualTo(newOwnerPage.helpBlock());

        newOwnerPage.clearFName();
        assertThat(newOwnerPage.requiredFirst).isEqualTo(newOwnerPage.helpBlock());
    }
    @Test
    public void lastNameValidationTests() {
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setLastName("p");
        assertThat(newOwnerPage.lastNamelongValidation).isEqualTo(newOwnerPage.helpBlock());

        newOwnerPage.clearLastName();
        assertThat(newOwnerPage.requiredLast).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test
    public void addressValidationTest() {
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setAddress("*");
        newOwnerPage.clearAddress();
        assertThat(newOwnerPage.address).isEqualTo(newOwnerPage.helpBlock());
    }
    @Test
    public void cityValidationTest() {
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setCity("-");
        newOwnerPage.clearCity();
        assertThat(newOwnerPage.city).isEqualTo(newOwnerPage.helpBlock());
    }

    @Test
    public void telephoneTest() {
        goToOwnersPage();
        OwnersPage ownersPage = new OwnersPage(driver);
        NewOwnerPage newOwnerPage = ownersPage.clickAddOwnerBtn();
        newOwnerPage.setTelephone(" ");
        assertThat(newOwnerPage.telephone).isEqualTo(newOwnerPage.helpBlock());

        newOwnerPage.clearTelephone();
        assertThat(newOwnerPage.telephoneRequired).isEqualTo(newOwnerPage.helpBlock());
    }

}
