package com.auto.PageObjectTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserBuilder {
//            WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
    private WebDriver driver;
    private WebDriverManager manager;

    public ChromeDriver Chrome(WebDriver driver) {
        this.driver = driver;
        return new ChromeDriver();
    }
    public FirefoxDriver FF(WebDriver driver) {
        this.driver = driver;
        return new FirefoxDriver();
    }
//    public WebDriver lpl{
//        return driver;
//    }
//            WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();

//    public BrowserBuilder setDriver(WebDriver driver) {
//        this.driver = driver;
//        return this;
//    }
}
