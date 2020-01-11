package com.auto.PageObjectTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.auto.PageObjectTests.DriverType.CHROME;
import static com.auto.PageObjectTests.DriverType.FIREFOX;

public class DriverManager {
    public static WebDriver getManager(DriverType type) {
//        DriverType chrome = DriverType.CHROME;
//        DriverType firefox = DriverType.FIREFOX;

        WebDriver driver;

        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new FirefoxDriver();
                break;
        }
        return driver;

    }
}
