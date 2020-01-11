package com.auto;

import com.auto.PageObjectTests.BrowserSelect;
import com.auto.PageObjectTests.PetTypePage;
import com.auto.PageObjectTests.VeterinariansPage;
import com.auto.PageObjectTests.WebDriverFactory;
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
    private static final String DEFAULT_URL = "http://localhost:8000/petclinic";
    public WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        //driver = WebDriverFactory.getDriver();
        driver = WebDriverFactory.getManager();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

        protected void goToHomePage() {
        goToUrl(DEFAULT_URL + "/welcome", "Welcome");
    }
    protected void goToOwnersPage() {
        goToUrl(DEFAULT_URL + "/owners", "Owners");
    }

    protected VeterinariansPage goToVetsPage() {
        goToUrl(DEFAULT_URL + "/vets", "Veterinarians");
        return null;
    }

    protected void goToPetTypesPage() {
        goToUrl(DEFAULT_URL + "/pettypes",  "Pet Types");
    }

    protected void goToSpecialtiesPage() {
        goToUrl(DEFAULT_URL + "/specialties",  "Specialties");
    }

    protected WebDriverWait waitFor() {
        return new WebDriverWait(driver, 4);
    }

    protected void goToUrl(String url, String title) {
        driver.get(url);
        waitFor().withMessage(title+ " page is not open!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), title));
    }
        protected WebDriverWait waiting() {
        return new WebDriverWait(driver, 2);
    }
    public void assertUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url);
    }
    protected String home = DEFAULT_URL + "/welcome";
    protected String owners = DEFAULT_URL + "/owners";
    protected String vets = DEFAULT_URL + "/vets";
    protected String petTypes = DEFAULT_URL + "/pettypes";
    protected String specPage = DEFAULT_URL + "/specialties";
    String ownersList = "td.ownerFullName";
    String vetsList = "//*[@id='vets']/tbody/tr";
    String petsAndSpecList = "//tbody/tr";

//
//    public TestPreconditions(WebDriver driver) {
//    }

//    @BeforeClass
//    public void setUpDriver() {
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//    }
//
//    @BeforeMethod
//    public void setUp() {
//        driver = WebDriverFactory.getDriver();
//       // driver = Configuration.getBrowser().browser();
//        //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
//    protected String home = DEFAULT_URL + "/welcome";
//    protected String owners = DEFAULT_URL + "/owners";
//    protected String vets = DEFAULT_URL + "/vets";
//    protected String petTypes = DEFAULT_URL + "/pettypes";
//    protected String specPage = DEFAULT_URL + "/specialties";
//
//    String ownersList = "td.ownerFullName";
//    String vetsList = "//*[@id='vets']/tbody/tr";
//    String petsAndSpecList = "//tbody/tr";
//
//
//
//    protected void goToHomePage() {
//        navigateToPage(DEFAULT_URL + "/welcome", "Welcome");
//    }
//    protected void goToOwnersPage() {
//        navigateToPage(DEFAULT_URL + "/owners", "Owners");
//    }
//    protected VeterinariansPage goToVetsPage() {
//        navigateToPage(DEFAULT_URL + "/vets","Veterinarians");
//        return new VeterinariansPage(driver);
//    }
//    protected void goToAddVetPage() {
//        navigateToPage(DEFAULT_URL + "/vets/add","New Veterinarian");
//    }
//    protected PetTypePage goToPetTypesPage() {
//        navigateToPage(DEFAULT_URL + "/pettypes",  "Pet Types");
//        return new PetTypePage(driver);
//    }
//    protected void goToSpecialtiesPage() {
//        navigateToPage(DEFAULT_URL + "/specialties",  "Specialties");
//    }
//    protected void navigateToPage(String url, String title){
//        driver.get(url);
//        waiting().withMessage(title + " page was not opened. Something went wrong...").
//                until(ExpectedConditions.textToBe(By.xpath("//h2"), title));
//    }
//    protected WebDriverWait waiting() {
//        return new WebDriverWait(driver, 2);
//    }
//    public void assertUrl(String url){
//        String currentUrl = driver.getCurrentUrl();
//        assertEquals(currentUrl, url);
//    }

}
