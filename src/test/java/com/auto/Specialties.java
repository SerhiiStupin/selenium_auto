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

import static org.testng.Assert.assertEquals;

public class Specialties {
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
        navigateToSpecPage();
        String pageHead = "Specialties";
        String currentPage = driver.findElement(By.xpath("//h2")).getText();
        assertEquals(pageHead, currentPage);
    }
    @Test
    public void specAddTest() {
        driver.get(home);
        navigateToSpecPage();
        List<WebElement> before = driver.findElements(By.xpath("//tbody/tr"));
        specAddButton();
        nameInput("LOR");
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addingEmptySpec(){
        driver.get(home);
        navigateToSpecPage();
        assertUrl(driver.getCurrentUrl());
        List<WebElement> before = driver.findElements(By.xpath("//tbody/tr"));
        specAddButton();
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size(), after.size());
    }
    @Test
    public void specCheck(){
        driver.get(home);
        navigateToSpecPage();
        specAddButton();
        String spec = "Nurse";
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(spec);
        saveVetButtonClick();
        String specCheck = driver.findElement(By.xpath("//tbody/tr[last()]/td/input")).getAttribute("ng-reflect-model");
        assertEquals(spec, specCheck);
    }
    @Test
    public void editPetTypeTest(){
        driver.get(home);
        navigateToSpecPage();
        String newType = "Surgeon";
        driver.findElement(By.xpath("//tr[5]/td[2]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='name']")).clear();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Update']")).click();
        navigateToSpecPage();
        String nameCheck = driver.findElement(By.xpath("//tr[5]/td/input")).getAttribute("ng-reflect-model");
        assertEquals(newType, nameCheck);
    }
    @Test
    public void editPetTypeCancelTest(){
        driver.get(home);
        navigateToSpecPage();
        String oldType = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute("ng-reflect-model");
        String newType = "Horse with osteohondroz";
        driver.findElement(By.xpath("//tr[1]/td[2]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='name']")).clear();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(newType);
        driver.findElement(By.xpath("//*[text()='Cancel']")).click();
        navigateToSpecPage();
        String nameCheck = driver.findElement(By.xpath("//tr[1]/td/input")).getAttribute("ng-reflect-model");
        assertEquals(oldType, nameCheck);
    }
    @Test
    public void petTypeDeleteTest() {
        driver.get(home);
        navigateToSpecPage();
        List<WebElement> before = driver.findElements(By.xpath("//tbody/tr"));
        specAddButton();
        nameInput("Godzilllaaaaaa");
        saveVetButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size()+1, after.size());
        driver.findElement(By.xpath("//tbody/tr[last()]/td/button[2]")).click();
        List<WebElement> afterDelete = driver.findElements(By.xpath("//tbody/tr"));
        assertEquals(before.size(), afterDelete.size());
    }
    String home = "http://localhost:8000/petclinic/welcome";
    String specPage = "http://localhost:8000/petclinic/specialties";

    public void navigateToSpecPage(){
        driver.findElement(By.xpath("//*[@routerlink='/specialties']")).click();
    }
    public void specAddButton(){
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
