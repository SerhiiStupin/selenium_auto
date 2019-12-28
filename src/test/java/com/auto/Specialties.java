package com.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class Specialties extends TestPreconditions{

    @Test
    public void pageCheck(){
        goToSpecialtiesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void specAddTest() {
        String name = "LOR";
        goToHomePage();
        navigateToSpecPageUsingMenu();
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        specAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(name);
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addingEmptySpec(){
        goToSpecialtiesPage();
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        specAddButton();
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size(), after.size());
    }
    @Test
    public void specCheck(){
        String spec = "Nurse";
        goToSpecialtiesPage();
        specAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(spec);
        saveVetButtonClick();
        driver.navigate().refresh();
        String specCheck = driver.findElement(By.xpath("//tbody/tr[last()]/td/input")).getAttribute(attribute);
        assertEquals(spec, specCheck);
    }
    @Test
    public void editSpecTest(){
        String newType = "Surgeon";
        goToSpecialtiesPage();
        driver.findElement(By.xpath("//tr[last()]/td[2]/button[1]")).click();
        driver.findElement(By.xpath(nameIdLocator)).clear();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Update']")).click();
        navigateToSpecPageUsingMenu();
        String nameCheck = driver.findElement(By.xpath("//tr[last()]/td/input")).getAttribute(attribute);
        assertEquals(newType, nameCheck);
    }
    @Test
    public void editSpecCancelTest(){
        String newType = "Horse with osteohondroz";
        goToSpecialtiesPage();
        String oldType = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute(attribute);
        driver.findElement(By.xpath("//tr[1]/td[2]/button[1]")).click();
        driver.findElement(By.xpath(nameIdLocator)).clear();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Cancel']")).click();
        navigateToSpecPageUsingMenu();
        String nameCheck = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute(attribute);
        assertEquals(oldType, nameCheck);
    }
    @Test
    public void petTypeDeleteTest() {
        String name = "Godzilllaaaaaa";
        goToSpecialtiesPage();
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        specAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(name);
        saveVetButtonClick();
        driver.findElement(By.xpath("//tbody/tr[last()]/td/button[2]")).click();
        driver.navigate().refresh();
        List<WebElement> afterDelete = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size(), afterDelete.size());
    }
    String attribute = "ng-reflect-model";
    String nameIdLocator = "//*[@id='name']";

    public void navigateToSpecPageUsingMenu(){
        driver.findElement(By.xpath("//*[@routerlink='/specialties']")).click();
    }
    public void specAddButton(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()=' Add ']")).click();
    }

    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Save']")).click();
    }
}
