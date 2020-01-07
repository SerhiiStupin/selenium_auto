package com.auto.PageObjectTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverInfo;
import org.testng.annotations.Test;

public class Testtest {
    @Test
    public void broTezt(){
        Configuration configuration = new Configuration();
        String chrome = String.valueOf(configuration.browser());
        System.out.println(chrome);
    }
}
