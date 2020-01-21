package com.auto.PageObjectTests;

import com.auto.APITests.Owner.ApiTestPreconditions;
import com.auto.TestPreconditions;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@Epic("Petclinic")
@Feature("Specialties")
public class SpecTests extends TestPreconditions {
    @Test(description = "Checking Spec page")
    @Story("Checking the URL")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("specialty.com")
    public void pageCheck(){
        goToSpecialtiesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test(description = "Adding a new Spec")
    @Story("Adding a new Spec")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("specialty.com")
    public void specialtyAddingTest() {
        String spec = "lor";
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.specCreationPrec();
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        driver.navigate().refresh();
        assertThat(specPage.specList()).isEqualTo(spec);
    }
    @Test(description = "Adding an empty Spec")
    @Severity(SeverityLevel.MINOR)
    @Story("Adding pet without name and specialty")
    @TmsLink("specialty.com")
    public void addingEmptySpec(){
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        List<WebElement> before = specPage.specialists();
        specPage.addBtn();
        specPage.saveBtn();
        List<WebElement> after = specPage.specialists();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test(description = "Delete the new Spec")
    @Severity(SeverityLevel.NORMAL)
    @Story("Deleting of the Spec")
    @TmsLink("specialty.com")
    public void specDeleteTest() {
        String spec = "lor";
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        List<WebElement> before = specPage.specialists();
        ApiTestPreconditions apiPrec = new ApiTestPreconditions();
        apiPrec.setUp();
        apiPrec.specCreationPrec();
        goToSpecialtiesPage();
        assertThat(specPage.specList()).isEqualTo(spec);
        List<WebElement> afterAdding = specPage.specialists();
        assertThat(before.size()+1).isEqualTo(afterAdding.size());
        specPage.deleteLast();
        driver.navigate().refresh();
        List<WebElement> afterDeleting = specPage.specialists();
        assertThat(before.size()).isEqualTo(afterDeleting.size());
    }
    @Test(description = "Returning to the home page")
    @Severity(SeverityLevel.MINOR)
    @Story("Returning to the home page")
    @TmsLink("specialty.com")
    public void homeButtonTest() {
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        specPage.homeBtn();
        assertUrl(home);
    }
}
//    @Test
//    public void specialtyAddingTest() {
//        String spec = "X-Ray";
//        goToSpecialtiesPage();
//        SpecPage specPage = new SpecPage(driver);
//        specPage.addBtn();
//        specPage.setName(spec);
//        specPage.saveBtn();
//        driver.navigate().refresh();
//        assertThat(specPage.specList()).isEqualTo(spec);
//    }
//@Test
//public void petTypeDeleteTest() {
//    String spec = "LOR";
//    goToSpecialtiesPage();
//    SpecPage specPage = new SpecPage(driver);
//    List<WebElement> before = specPage.specialists();
//    specPage.addBtn();
//    specPage.setName(spec);
//    specPage.saveBtn();
//    driver.navigate().refresh();
//    assertThat(specPage.specList()).isEqualTo(spec);
//    List<WebElement> afterAdding = specPage.specialists();
//    assertThat(before.size()+1).isEqualTo(afterAdding.size());
//    specPage.deleteLast();
//    driver.navigate().refresh();
//    List<WebElement> afterDeleting = specPage.specialists();
//    assertThat(before.size()).isEqualTo(afterDeleting.size());
//}