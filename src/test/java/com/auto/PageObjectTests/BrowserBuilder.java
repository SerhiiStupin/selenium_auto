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
    private WebDriver chrome;
    private WebDriver firefox;

    public ChromeDriver Chrome(WebDriver chrome) {
        this.driver = chrome;
        return (ChromeDriver) chrome;
    }
    public FirefoxDriver FF(WebDriver firefox) {
        this.driver = firefox;
        return (FirefoxDriver) firefox;
    }
    public WebDriver build(){
        return chrome;
    }
    public WebDriver build1(){
        return firefox;
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
