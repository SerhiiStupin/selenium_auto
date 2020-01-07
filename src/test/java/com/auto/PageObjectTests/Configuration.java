package com.auto.PageObjectTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Configuration {
    private static Configuration browser;
    //public WebDriver driver;
    public ChromeDriver chrome;
    public FirefoxDriver firefox;

    public static Configuration getBrowser(){
        if (browser == null){
            browser = new Configuration();
        }
        return browser;
    }
//    public WebDriver setUpBrowser() {
//        chrome = new ChromeDriver();
//    return chrome;
//    }
    public WebDriver setUpBrowser() {
        firefox = new FirefoxDriver();
        return firefox;
    }
    public WebDriver browser(){
        return setUpBrowser();
    }

}
//    public void setUpDriver() {
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//    }

//    public WebDriver setUpBrowser() {
//        driver = new ChromeDriver();
//        return driver;
//    }
//    public WebDriver setUpBrowser() {
//        driver = new FirefoxDriver();
//        return driver;
//    }
//    public WebDriver browser(){
//        return setUpBrowser();
//    }
/*
    private static Configuration browser;
    public WebDriver driver;
    private ChromeDriver chrome;
    private FirefoxDriver firefox;

    public static Configuration getBrowser(){
        if (browser == null){
            browser = new Configuration();
        }
        return browser;
    }
//    Configuration() {
//        initChrome();
//        initFF();
//    }


    public WebDriver Chrome() {
    return (WebDriver) chrome;
}
    public WebDriver Firefox() {
        return (WebDriver) firefox;}

    private void initChrome() {
        this.chrome = new BrowserBuilder()
                .Chrome(new ChromeDriver());}
    private void initFF(){
        this.firefox = new BrowserBuilder()
                .FF(new FirefoxDriver());
    }

 */