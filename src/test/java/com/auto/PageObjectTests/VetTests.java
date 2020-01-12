package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class VetTests extends TestPreconditions {

    @Test
    public void addVetTest() {
        VeterinariansPage veterinariansPage = goToVetsPage();

        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        newVeterPage.setFirstName("First");
        newVeterPage.setLastName("Test");
        newVeterPage.specTypeList(1);

        veterinariansPage = newVeterPage.saveVetButtonClick();
        goToVetsPage();
        List<String> vets = veterinariansPage.getVetsList();
        assertTrue(vets.contains("First Test"));
    }

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

    @Test
    public void addEmptyVet(){
        VeterinariansPage veterinariansPage = goToVetsPage();
        List<WebElement> before = veterinariansPage.veterinariansList();
        NewVeterPage newVeterPage = veterinariansPage.clickAddBtn();
        veterinariansPage = newVeterPage.saveVetButtonClick();
        goToVetsPage();
        List<WebElement> after = veterinariansPage.veterinariansList();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test
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
    @Test
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
    @Test
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
    @Test
    public void homeButtonTest() {
        VeterinariansPage veterinariansPage = goToVetsPage();
        veterinariansPage.homeBtn();
        assertUrl(home);
    }
}
