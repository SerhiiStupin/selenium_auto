package com.auto.PageObjectTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public enum BrowserSelect {
        FIREFOX,
        CHROME;


        //    CHROME(WebDriverManager.chromedriver().setup()),
//    FIREFOX(WebDriverManager.firefoxdriver().setup());
//
//    private WebDriver browser;
//
//    BrowserSelect(WebDriver browser) {
//        this.browser = browser;
//    }
//
//    public WebDriver getBrowser() {
//        return browser;
//    }

//    String browser = Configuration.getInstance().getBrowser();
//    WebDriver driver;
//
//        switch (browser.toLowerCase()) {
//        case "chrome":
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            break;
//        case "firefox":
//            WebDriverManager.firefoxdriver().setup();
//            driver =  new FirefoxDriver();
//            break;
//        default:
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//    }
//
//        driver.manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);
//
//        return driver;

}
