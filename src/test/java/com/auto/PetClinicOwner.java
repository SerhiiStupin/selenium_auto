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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PetClinicOwner {
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
    public void addNewOwnerTest(){
        String firstName = "James";
        String lastName = "Bond";
        String fullName = firstName + " " + lastName;

        driver.get("http://localhost:8000/petclinic/welcome");
        WebElement menuOwnerDropdown = driver.findElement(By.className("dropdown-toggle"));
        menuOwnerDropdown.click();
        WebElement addNew = driver.findElement(By.cssSelector("a[href='/petclinic/owners/add']"));
        addNew.click();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("address")).sendKeys("Some Street");
        driver.findElement(By.id("city")).sendKeys("London");
        driver.findElement(By.id("telephone")).sendKeys("2151212");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();

        List<WebElement> quantity = driver.findElements(By.className("ownerFullName"));
        assertEquals(quantity.size(), 12);
        String owner = String.valueOf(driver.findElement(By.partialLinkText("James Bond")).getText());
        assertTrue(owner.contains(fullName));

//        List<String> owner = new ArrayList<>();
//        for (WebElement owners : quantity) {
//            WebElement ownerName = owners.findElement(By.cssSelector("td.ownerFullName"));
//            owner.addAll(Collections.singleton(ownerName.getText()));
//            System.out.println(owner);
//        }
//        System.out.println(owner);
    }
    @Test
    public void backButtonTest(){
        String owners = "http://localhost:8000/petclinic/owners";
        driver.get("http://localhost:8000/petclinic/welcome");
        WebElement menuOwnerDropdown = driver.findElement(By.className("dropdown-toggle"));
        menuOwnerDropdown.click();
        WebElement addNew = driver.findElement(By.cssSelector("a[href='/petclinic/owners/add']"));
        addNew.click();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        assertEquals(owners, driver.getCurrentUrl());
    }
    @Test
    public void backButtonFromOwnerTest(){
        String owners = "http://localhost:8000/petclinic/owners";
        driver.get(owners);
        driver.findElement(By.partialLinkText("James Bond")).click();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        assertEquals(owners, driver.getCurrentUrl());
    }

    @Test
    public void firstNameValidationTests(){
        String firstName = "First name must be at least 2 characters long";
        String required = "First name is required";
        driver.get("http://localhost:8000/petclinic/owners");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        driver.findElement(By.id("firstName")).sendKeys("1");
        String helpBlock = driver.findElement(By.className("help-block")).getText();
        assertEquals(firstName, helpBlock);
        driver.findElement(By.id("firstName")).sendKeys(Keys.BACK_SPACE);
        String helpBlock2 = driver.findElement(By.className("help-block")).getText();
        assertEquals(required, helpBlock2);
    }
    @Test
    public void lastNameValidationTests(){
        String lastName = "Last name must be at least 2 characters long";
        String required = "Last name is required";
        driver.get("http://localhost:8000/petclinic/owners");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        driver.findElement(By.id("lastName")).sendKeys("q");
        String helpBlock = driver.findElement(By.className("help-block")).getText();
        assertEquals(lastName, helpBlock);
        driver.findElement(By.id("lastName")).sendKeys(Keys.BACK_SPACE);
        String helpBlock2 = driver.findElement(By.className("help-block")).getText();
        assertEquals(required, helpBlock2);
    }
    @Test
    public void addressValidationTest() {
        String address = "Address is required";
        driver.get("http://localhost:8000/petclinic/owners");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        driver.findElement(By.id("address")).sendKeys("-");
        driver.findElement(By.id("address")).sendKeys(Keys.BACK_SPACE);
        String message = driver.findElement(By.className("help-block")).getText();
        assertEquals(address, message);
    }
    @Test
    public void telephoneTest(){
        String phoneError = "Phone number only accept digits";
        String required = "Phone number is required";
        driver.get("http://localhost:8000/petclinic/owners");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        driver.findElement(By.id("telephone")).sendKeys("q");
        String errorMessage = driver.findElement(By.className("help-block")).getText();
        assertEquals(phoneError, errorMessage);
        driver.findElement(By.id("telephone")).sendKeys(Keys.BACK_SPACE);
        String helpBlock2 = driver.findElement(By.className("help-block")).getText();
        assertEquals(required, helpBlock2);
    }

    public void assertUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }
}
