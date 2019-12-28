package com.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TestPreconditions {
    public WebDriver driver;

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
    String DEFAULT_URL = "http://localhost:8000/petclinic";
    String home = DEFAULT_URL + "/welcome";
    String owners = DEFAULT_URL + "/owners";
    String vets = DEFAULT_URL + "/vets";
    String petTypes = DEFAULT_URL + "/pettypes";
    String specPage = DEFAULT_URL + "/specialties";

    String ownersList = "td.ownerFullName";
    String vetsList = "//*[@id='vets']/tbody/tr";
    String petsAndSpecList = "//tbody/tr";


    protected void goToHomePage() {
        navigateToPage(DEFAULT_URL + "/welcome", "Welcome");
    }
    protected void goToOwnersPage() {
        navigateToPage(DEFAULT_URL + "/owners", "Owners");
    }
    protected void goToVetsPage() {
        navigateToPage(DEFAULT_URL + "/vets","Veterinarians");
    }
    protected void goToPetTypesPage() {
        navigateToPage(DEFAULT_URL + "/pettypes",  "Pet Types");
    }
    protected void goToSpecialtiesPage() {
        navigateToPage(DEFAULT_URL + "/specialties",  "Specialties");
    }
    protected void navigateToPage(String url, String title){
        driver.get(url);
        waiting().withMessage(title + " page was not opened. Something went wrong...").
                until(ExpectedConditions.textToBe(By.xpath("//h2"), title));
    }
    protected WebDriverWait waiting() {
        return new WebDriverWait(driver, 2);
    }
    public void assertUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }

}