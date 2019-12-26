package com.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import static org.testng.Assert.assertTrue;

public class Veterinarians {
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
        driver.get(vets);
        String pageHead = "Veterinarians";
        String currentPage = driver.findElement(By.xpath("//h2")).getText();
        assertEquals(pageHead, currentPage);
    }
    @Test
    public void vetAddTest(){
        driver.get(vets);
        List<WebElement> before = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
        driver.get(home);
        navigateToAddVet();
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Doctor");
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("iBolit");
        driver.findElement(By.xpath("//*[@id='specialties']")).click();
        driver.findElement(By.xpath("//*[@value='2: Object']")).click();
        saveVetButtonClick();
        driver.get(vets);
        List<WebElement> after = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
        assertEquals(before.size()+1, after.size());
    }
    @Test
    public void addEmptyVet(){
        driver.get(vets);
        List<WebElement> before = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
        driver.get(home);
        navigateToAddVet();
        saveVetButtonClick();
        driver.get(vets);
        List<WebElement> after = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
        assertEquals(before.size(), after.size());
    }
    @Test
    public void firstNameValidationTests() {
        String longValidation = "First name must be at least 2 characters long";
        String requiredFirst = "First name is required";
        String firstName = "firstName";
        driver.get(home);
        navigateToAddVet();
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("*");
        helpBlockGetText(firstName);
        validationesAssert(longValidation);

        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText(firstName);
        validationesAssert(requiredFirst);
    }
    @Test
    public void lastNameValidationTests(){
        String longValidation2 = "Last name must be at least 2 characters long";
        String requiredLast = "Last name is required";
        String lastName = "lastName";
        driver.get(home);
        navigateToAddVet();
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("q");
        helpBlockGetText(lastName);
        validationesAssert(longValidation2);

        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText(lastName);
        validationesAssert(requiredLast);
    }
    @Test
    public void createVetWithoutType(){
        driver.get(vets);
        List<WebElement> before = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
        driver.get(home);
        navigateToAddVet();
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Doctor");
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("iBolit");
        saveVetButtonClick();
        driver.get(vets);

        isTypeEmpty(before.size()+1);
    }
    @Test
    public void homeButtonTest(){
        driver.get(vets);
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Home']")).click();
        String homePage = "http://localhost:8000/petclinic/welcome";
        assertUrl(homePage);
    }
    @Test
    public void backButtonVetsPageTest() {
        driver.get(vets);
        driver.findElement(By.xpath("//tbody/tr[1]/td")).click();
        driver.findElement(By.xpath("//tbody/tr[1]/td[3]/button[text()='Edit Vet']")).click();
        driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='< Back']")).click();
        assertEquals(vets, driver.getCurrentUrl());
    }


    String home = "http://localhost:8000/petclinic/welcome";
    String vets = "http://localhost:8000/petclinic/vets";

    public void beforeList(){
        driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
    }
    public void afterList(){
        driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
    }
    public void navigateToAddVet(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][text()='Veterinarians']")).click();
        driver.findElement(By.xpath("//a[@routerlink='/vets/add']")).click();
    }
    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[text()='Save Vet']")).click();
    }
//    public void assertVetsBefore(int after){
//        driver.get(vets);
//        List<WebElement> before = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
//        assertEquals(before.size(), after);
//    }
    public void helpBlockGetText(String name){
    driver.findElement(By.xpath("//*[@id='" + name + "']/following-sibling::span[2]")).getText();
}
    public void validationesAssert(String helpBlock){
        String blockText = driver.findElement(By.xpath("//span[@class='help-block']")).getText();
        assertEquals(blockText, helpBlock);
    }
    public void isTypeEmpty(int userId){
        driver.findElement(By.xpath("//tr[" + userId +"]/td[2]")).getText();
    }
    public void assertUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }
}
