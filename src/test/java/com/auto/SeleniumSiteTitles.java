package com.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SeleniumSiteTitles {
    private WebDriver driver;

    @BeforeClass
    public void driver(){WebDriverManager.chromedriver().setup();}
    @BeforeMethod
    public void setDriver(){driver = new ChromeDriver();}
    @AfterMethod
    public void finish(){driver.close();}

    @Test
    public void aboutPageTest(){
        String aboutUrl = "https://selenium.dev/about/";
        driver.get("https://selenium.dev/about/");
        assertURL(aboutUrl);
        String aboutTitle = "About Selenium";
        assertPageTitle(aboutTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
    }
    @Test
    public void eventsTest(){
        String eventsUrl = "https://selenium.dev/events/";
        driver.get("https://selenium.dev/events/");
        assertURL(eventsUrl);
        String eventsTitle = "Selenium Events";
        assertPageTitle(eventsTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
    }
    @Test
    public void ecosystemTest(){
        String ecosystemPage = "https://selenium.dev/ecosystem/";
        driver.get("https://selenium.dev/ecosystem/");
        assertURL(ecosystemPage);
        String ecosystemTitle = "Ecosystem";
        assertPageTitle(ecosystemTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
        String mainTitle = "SeleniumHQ Browser Automation";
        assertPageTitle(mainTitle);
    }
    @Test
    public void historyTest(){
        String historyPage = "https://selenium.dev/history/";
        driver.get("https://selenium.dev/history/");
        assertURL(historyPage);
        String historyTitle = "Selenium History";
        assertPageTitle(historyTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
        String confUrl = "https://selenium.dev/blog/2019/seleniumconf-london-2019/";
        driver.get("https://selenium.dev/blog/2019/seleniumconf-london-2019/");
        assertURL(confUrl);
        String confTitle = "SeleniumConf London 2019 Videos and Pictures";
        assertPageTitle(confTitle);
    }
    @Test
    public void involvedTest(){
        String involvedPage = "https://selenium.dev/getinvolved/";
        driver.get("https://selenium.dev/getinvolved/");
        assertURL(involvedPage);
        String involvedTitle = "Get Involved";
        assertPageTitle(involvedTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
        String mavenPage = "https://selenium.dev/maven/";
        driver.get("https://selenium.dev/maven/");
        assertURL(mavenPage);
        String mavenTitle = "Selenium Maven Information";
        assertPageTitle(mavenTitle);
    }
    @Test
    public void sponsorsPageTest(){
        String sponsorsPage = "https://selenium.dev/sponsors/";
        driver.get("https://selenium.dev/sponsors/");
        assertURL(sponsorsPage);
        String sponsorsTitle = "Sponsor";
        assertPageTitle(sponsorsTitle);
//        String homeUrl = "https://selenium.dev/";
//        driver.navigate().to("https://selenium.dev/");
//        asserMain(homeUrl);
        String linkToHome = "https://selenium.dev/";
        driver.findElement(By.className("headerLink")).click();
        driver.getCurrentUrl();
        assertURL(linkToHome);
    }
    @Test
    public void downloadsPageTest(){
        String downloadsPage = "https://selenium.dev/downloads/";
        driver.get("https://selenium.dev/downloads/");
        assertURL(downloadsPage);
        String downloadsTitle = "Downloads";
        assertPageTitle(downloadsTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
        String driverPage = "https://selenium.dev/projects/#selenium-webdriver";
        driver.get("https://selenium.dev/projects/#selenium-webdriver");
        assertURL(driverPage);
        String driverTitle = "Selenium Projects";
        assertPageTitle(driverTitle);
    }
    @Test
    public void projectPageTest(){
        String projectPage = "https://selenium.dev/projects/";
        driver.get("https://selenium.dev/projects/");
        assertURL(projectPage);
        String projectTitle = "Selenium Projects";
        assertPageTitle(projectTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
    }
    @Test
    public void documentationPageTest(){
        String documentPage = "https://selenium.dev/documentation/en/";
        driver.get("https://selenium.dev/documentation/en/");
        assertURL(documentPage);
        String documentTitle = "The Selenium Browser Automation Project :: Documentation for Selenium";
        assertPageTitle(documentTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
    }
    @Test
    public void supportPageTest(){
        String supportPage = "https://selenium.dev/support/";
        driver.get("https://selenium.dev/support/");
        assertURL(supportPage);
        String supportTitle = "Selenium Support";
        assertPageTitle(supportTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);
    }
    @Test
    public void blogPageTest(){
        String blogPage = "https://selenium.dev/blog/";
        driver.get("https://selenium.dev/blog/");
        assertURL(blogPage);
        String blogTitle = "Selenium Blog";
        assertPageTitle(blogTitle);
        String homeUrl = "https://selenium.dev/";
        driver.navigate().to("https://selenium.dev/");
        asserMain(homeUrl);

    }

    public void asserMain(String homeUrl){
        String mainUrl = driver.getCurrentUrl();
        assertEquals(mainUrl, homeUrl);
    }
    public void assertURL(String url){
        String currentURL = driver.getCurrentUrl();
        assertEquals(currentURL, url);}

    private void assertPageTitle(String pageTitle){
        String currentTitle = driver.getTitle();
        assertEquals(currentTitle, pageTitle);}


}
