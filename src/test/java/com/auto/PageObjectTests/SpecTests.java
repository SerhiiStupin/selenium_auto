package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecTests extends TestPreconditions {
//    public SpecTests(WebDriver driver) {
//        super(driver);
//    }

    @Test
    public void pageCheck(){
        goToSpecialtiesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void specialtyAddingTest() {
        String spec = "X-Ray";
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        specPage.addBtn();
        specPage.setName(spec);
        specPage.saveBtn();
        assertThat(specPage.specList()).isEqualTo(spec);
    }
    @Test
    public void addingEmptyPet(){
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        List<WebElement> before = specPage.specialists();
        specPage.addBtn();
        specPage.saveBtn();
        List<WebElement> after = specPage.specialists();
        assertThat(before.size()).isEqualTo(after.size());
    }
    @Test
    public void petTypeDeleteTest() {
        String spec = "LOR";
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        List<WebElement> before = specPage.specialists();
        specPage.addBtn();
        specPage.setName(spec);
        specPage.saveBtn();
        assertThat(specPage.specList()).isEqualTo(spec);
        List<WebElement> afterAdding = specPage.specialists();
        assertThat(before.size()+1).isEqualTo(afterAdding.size());
        specPage.deleteLast();
        List<WebElement> afterDeleting = specPage.specialists();
        assertThat(before.size()).isEqualTo(afterDeleting.size());
    }
    @Test
    public void homeButtonTest() {
        goToSpecialtiesPage();
        SpecPage specPage = new SpecPage(driver);
        specPage.homeBtn();
        assertUrl(home);
    }
}
