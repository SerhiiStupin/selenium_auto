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
    public void addNewOwnerTest() {
        driver.get(owners);
        List<WebElement> before = driver.findElements(By.xpath("//*[@class='ownerFullName']"));
        driver.get(home);
        goToAddOwnerPage();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("address")).sendKeys("Some Street");
        driver.findElement(By.id("city")).sendKeys("London");
        driver.findElement(By.id("telephone")).sendKeys("2151212");
        addOwnerButtonClick();
        List<WebElement> after = driver.findElements(By.xpath("//*[@class='ownerFullName']"));
        assertEquals(before.size()+1, after.size());
    }

    @Test
    public void backButtonTest() {
        driver.get(home);
        goToAddOwnerPage();
        assertUrl(driver.getCurrentUrl());
        backButtonClick();
        assertEquals(owners, driver.getCurrentUrl());
    }

    @Test
    public void backButtonFromOwnerTest() {
        driver.get(owners);
        driver.findElement(By.partialLinkText("James Bond")).click();
        assertUrl(driver.getCurrentUrl());
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        assertEquals(owners, driver.getCurrentUrl());
    }

    @Test
    public void firstNameValidationTests() {
        String firstName = "First name must be at least 2 characters long";
        String required = "First name is required";
        driver.get(owners);
        addOwnerButtonClick();
        driver.findElement(By.id("firstName")).sendKeys("1");
        helpBlockGetText();
        validationesAssert(firstName);

        driver.findElement(By.id("firstName")).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationesAssert(required);
    }

    @Test
    public void lastNameValidationTests() {
        String lastName = "Last name must be at least 2 characters long";
        String required = "Last name is required";
        driver.get(owners);
        addOwnerButtonClick();
        driver.findElement(By.id("lastName")).sendKeys("q");
        helpBlockGetText();
        validationesAssert(lastName);

        driver.findElement(By.id("lastName")).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationesAssert(required);
    }

    @Test
    public void addressValidationTest() {
        String address = "Address is required";
        driver.get(owners);
        addOwnerButtonClick();
        driver.findElement(By.id("address")).sendKeys("-");
        driver.findElement(By.id("address")).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationesAssert(address);
    }

    @Test
    public void telephoneTest() {
        String phoneError = "Phone number only accept digits";
        String required = "Phone number is required";
        driver.get(owners);
        addOwnerButtonClick();
        driver.findElement(By.id("telephone")).sendKeys("q");
        helpBlockGetText();
        validationesAssert(phoneError);

        driver.findElement(By.id("telephone")).sendKeys(Keys.BACK_SPACE);
        helpBlockGetText();
        validationesAssert(required);
    }

    public void assertUrl(String url) {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }

    public void goToAddOwnerPage() {
        WebElement menuOwnerDropdown = driver.findElement(By.className("dropdown-toggle"));
        menuOwnerDropdown.click();
        WebElement addNew = driver.findElement(By.cssSelector("a[href='/petclinic/owners/add']"));
        addNew.click();
    }

    public void addOwnerButtonClick(){
        driver.findElement(By.xpath("//*[text()='Add Owner']")).click();
    }
    public void backButtonClick(){
        driver.findElement(By.xpath("//*[text()='Back']")).click();
    }
    public void helpBlockGetText(){
        driver.findElement(By.xpath("//span[@class='help-block']")).getText();
    }
    public void validationesAssert(String helpBlock){
        String blockText = driver.findElement(By.xpath("//span[@class='help-block']")).getText();
        assertEquals(blockText, helpBlock);
    }

    String home = "http://localhost:8000/petclinic/welcome";
    String owners = "http://localhost:8000/petclinic/owners";
    String firstName = "James";
    String lastName = "Bond";
}
