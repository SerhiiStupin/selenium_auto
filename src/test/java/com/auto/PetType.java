package com.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.*;

public class PetType extends TestPreconditions{

//    public PetType(WebDriver driver) {
//        super(driver);
//    }

    @Test
    public void pageCheck(){
        goToPetTypesPage();
        assertUrl(driver.getCurrentUrl());
    }
    @Test
    public void petTypeAddTest() {
        String name = "Crocodile";
        goToPetTypesPage();
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        petTypeAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(name);
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addingEmptyPet(){
        goToPetTypesPage();
        assertUrl(driver.getCurrentUrl());
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        petTypeAddButton();
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size(), after.size());
    }
    @Test
    public void nameCheck() {
        String petType = "Monkey";
        goToPetTypesPage();
        petTypeAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(petType);
        saveVetButtonClick();
        driver.navigate().refresh();
        String nameCheck = driver.findElement(By.xpath("//tbody/tr[last()]/td/input")).getAttribute(attribute);
        assertEquals(petType, nameCheck);
    }
    @Test
    public void editPetTypeTest(){
        String newType = "Mozilla";
        driver.get(petTypes);
        driver.findElement(By.xpath("//tr[5]/td[2]/button[1]")).click();
        driver.findElement(By.xpath(nameIdLocator)).clear();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Update']")).click();
        navigateToPetTypesPage();
        String nameCheck = driver.findElement(By.xpath("//tr[5]/td/input")).getAttribute(attribute);
        assertEquals(newType, nameCheck);
    }
    @Test
    public void editPetTypeCancelTest(){
        String newType = "Sobaka";
        driver.get(petTypes);
        String oldType = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute(attribute);
        driver.findElement(By.xpath("//tr[1]/td[2]/button[1]")).click();
        driver.findElement(By.xpath(nameIdLocator)).clear();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Cancel']")).click();
        navigateToPetTypesPage();
        String nameCheck = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute(attribute);
        assertEquals(oldType, nameCheck);
    }
    @Test
    public void petTypeDeleteTest() {
        String newtype = "Zombie";
        driver.get(petTypes);
        List<WebElement> before = driver.findElements(By.xpath(petsAndSpecList));
        petTypeAddButton();
        driver.findElement(By.xpath(nameIdLocator)).sendKeys(newtype);
        saveVetButtonClick();
        driver.findElement(By.xpath("//tbody/tr[last()]/td/button[text()='Delete']")).click();
        driver.navigate().refresh();
        List<WebElement> afterDelete = driver.findElements(By.xpath(petsAndSpecList));
        assertEquals(before.size(), afterDelete.size());
    }
    String attribute = "ng-reflect-model";
    String nameIdLocator = "//*[@id='name']";

    public void navigateToPetTypesPage(){
        driver.findElement(By.xpath("//*[@routerlink='/pettypes']")).click();
    }
    public void petTypeAddButton(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()=' Add ']")).click();
    }
    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Save']")).click();
    }

}
