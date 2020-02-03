package Listener;

import com.auto.PageObjectTests.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverHolder {
    private static WebDriver driver;
    public static WebDriverHolder INSTANCE = new WebDriverHolder();

    public WebDriverHolder() {
        if (driver == null){
            driver = WebDriverFactory.getDriver();
        }
    }
    public WebDriver getDriver(){
        return driver;
    }
}
