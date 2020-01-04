package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTests extends TestPreconditions {

    @Test
    public void pageCheck(){
        goToPetTypesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void petTypeAddTest() {
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        petTypePage.addBtn();
        petTypePage.setName("Alligator");
        petTypePage.saveBtn();
        assertThat(petTypePage.typeList()).isEqualTo("Alligator");
    }
    @Test
    public void addingEmptyPet(){
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        List<WebElement> before = petTypePage.petsList();
        petTypePage.addBtn();
        petTypePage.saveBtn();
        List<WebElement> after = petTypePage.petsList();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test
    public void petTypeDeleteTest() {
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        List<WebElement> before = petTypePage.petsList();
        petTypePage.addBtn();
        petTypePage.setName("Chupakabra");
        petTypePage.saveBtn();
        assertThat(petTypePage.typeList()).isEqualTo("Chupakabra");
        List<WebElement> afterAdding = petTypePage.petsList();
        assertThat(before.size()+1).isEqualTo(afterAdding.size());
        petTypePage.deleteLast();
        List<WebElement> afterDeleting = petTypePage.petsList();
        assertThat(before.size()).isEqualTo(afterDeleting.size());
    }
        @Test
    public void homeButtonTest() {
        goToPetTypesPage();
        PetTypePage petTypePage = new PetTypePage(driver);
        petTypePage.homeBtn();
        assertUrl(home);
        }
}
