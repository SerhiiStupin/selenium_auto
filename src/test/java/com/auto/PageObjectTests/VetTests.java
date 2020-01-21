package com.auto.PageObjectTests;

import com.auto.APITests.Owner.ApiTestPreconditions;
import com.auto.TestPreconditions;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;
@Epic("PetClinic")
@Feature("Veterinarians")
public class VetTests extends TestPreconditions {

    @Test(description = "Creating a new vet")
    @Story("Creating a new vet")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("vets.com")
    public void addVetTest() {
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.vetCreationPrec();
        VeterinariansPage veterinariansPage = goToVetsPage();
        List<String> vets = veterinariansPage.getVetsList();
        assertTrue(vets.contains("i Bolit"));
    }
    @Test(description = "Adding an empty vet")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and vet")
    @TmsLink("vets.com")
    public void addEmptyVet(){
        VeterinariansPage veterinariansPage = goToVetsPage();
        List<WebElement> before = veterinariansPage.veterinariansList();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        veterinariansPage = newVeterPage.saveVetButtonClick();
        goToVetsPage();
        List<WebElement> after = veterinariansPage.veterinariansList();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test(description = "FirstName field validation")
    @Story("FirstName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void firstNameValidationTests() {
        String firstName = "firstName";
        String firstNameLongValidation = "First name must be at least 2 characters long";
        String requiredFirst = "First name is required";
        VeterinariansPage veterinariansPage = goToVetsPage();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        newVeterPage.setFirstName("*");
        assertThat(newVeterPage.helpBlockGetText(firstName)).isEqualTo(firstNameLongValidation);

        newVeterPage.clearFirstName();
        assertThat(newVeterPage.helpBlockGetText(firstName)).isEqualTo(requiredFirst);
    }
    @Test(description = "LastName field validation")
    @Story("LastName field validation")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void lastNameValidationTests(){
        String requiredLast = "Last name is required";
        String lastName = "lastName";
        String lastNamelongValidation = "Last name must be at least 2 characters long";
        VeterinariansPage veterinariansPage = goToVetsPage();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        newVeterPage.setLastName("-");
        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(lastNamelongValidation);

        newVeterPage.clearLastName();
        assertThat(newVeterPage.helpBlockGetText(lastName)).isEqualTo(requiredLast);
    }
    @Test(description = "Adding vet without type")
    @Story("Adding vet without type")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("vets.com")
    public void createVetWithoutType(){
        VeterinariansPage veterinariansPage = goToVetsPage();
        List<WebElement> before = veterinariansPage.veterinariansList();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        newVeterPage.setFirstName("Sponge");
        newVeterPage.setLastName("Bob");
        veterinariansPage = newVeterPage.saveVetButtonClick();
        goToVetsPage();
        List<WebElement> after = veterinariansPage.veterinariansList();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("vets.com")
    public void homeButtonTest() {
        VeterinariansPage veterinariansPage = goToVetsPage();
        veterinariansPage.homeBtn();
        assertUrl(home);
    }
}
//    @Test
//    public void addVetTest() {
//        VeterinariansPage veterinariansPage = goToVetsPage();
//
//        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
//        newVeterPage.setFirstName("First");
//        newVeterPage.setLastName("Test");
//        newVeterPage.specTypeList(1);
//
//        veterinariansPage = newVeterPage.saveVetButtonClick();
//        goToVetsPage();
//        List<String> vets = veterinariansPage.getVetsList();
//        assertTrue(vets.contains("First Test"));
//    }

        /*Этот вариант почему-то не срабатывает. В списке все элементы = null. Еще над ним поработаю позже
        VeterinariansPage veterinariansPage = goToVetsPage();
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setFirstName("First");
        veterinarian.setLastName("Test");
        veterinarian.setType(1);

        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        newVeterPage.createVet(veterinarian);
        veterinariansPage = newVeterPage.saveVetButtonClick();

        List<Veterinarian> newVetlist = veterinariansPage.getVetsList();
        assertThat(newVetlist).contains(veterinarian);
         */