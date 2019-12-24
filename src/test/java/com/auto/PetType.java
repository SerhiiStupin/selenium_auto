package com.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class PetType {
    private WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void pageCheck(){
        driver.get(home);
        navigateToPetTypesPage();
        String pageHead = "Pet Types";
        String currentPage = driver.findElement(By.xpath("//h2")).getText();
        assertEquals(pageHead, currentPage);
    }
    @Test
    public void petTypeAddTest() {
        driver.get(home);
        navigateToPetTypesPage();
        List<WebElement> before = driver.findElements(By.xpath("//tbody/tr"));
        petTypeAddButton();
        nameInput("Crocodile");
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addingEmptyPet(){
        driver.get(home);
        navigateToPetTypesPage();
        assertUrl(driver.getCurrentUrl());
        List<WebElement> before = driver.findElements(By.xpath("//tbody/tr"));
        petTypeAddButton();
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size(), after.size());
    }
    @Test
    public void nameCheck(){
        driver.get(home);
        navigateToPetTypesPage();
        petTypeAddButton();
        String petType = "Monkey";
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(petType);
        saveVetButtonClick();
        String nameCheck = driver.findElement(By.xpath("//tbody/tr[last()]/td/input")).getAttribute("ng-reflect-model");
        assertEquals(petType, nameCheck);
    }
    @Test
    public void editPetTypeTest(){
        driver.get(home);
        navigateToPetTypesPage();
        String newType = "Mozilla";
        driver.findElement(By.xpath("//tr[5]/td[2]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='name']")).clear();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Update']")).click();
        navigateToPetTypesPage();
        String nameCheck = driver.findElement(By.xpath("//tr[5]/td/input")).getAttribute("ng-reflect-model");
        assertEquals(newType, nameCheck);
    }
    @Test
    public void editPetTypeCancelTest(){
        driver.get(home);
        navigateToPetTypesPage();
        String oldType = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute("ng-reflect-model");
        String newType = "Sobaka";
        driver.findElement(By.xpath("//tr[1]/td[2]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='name']")).clear();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Cancel']")).click();
        navigateToPetTypesPage();
        String nameCheck = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute("ng-reflect-model");
        assertEquals(oldType, nameCheck);
    }
    @Test
    public void petTypeDeleteTest() {
        driver.get(home);
        navigateToPetTypesPage();
        List<WebElement> before = driver.findElements(By.xpath("//tbody/tr"));
        petTypeAddButton();
        nameInput("Zombie");
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size()+1, after.size());
        driver.findElement(By.xpath("//tbody/tr[last()]/td/button[2]")).click();
        List<WebElement> afterDelete = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size(), afterDelete.size());
    }
    String home = "http://localhost:8000/petclinic/welcome";
    String petTypes = "http://localhost:8000/petclinic/pettypes";

    public void navigateToPetTypesPage(){
        driver.findElement(By.xpath("//*[@routerlink='/pettypes']")).click();
    }
    public void petTypeAddButton(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()=' Add ']")).click();
    }
    public void nameInput(String name){
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(name);
    }
    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Save']")).click();
    }
    public void assertUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }
}
