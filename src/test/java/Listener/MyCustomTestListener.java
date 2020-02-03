package Listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class MyCustomTestListener implements ISuiteListener, ITestListener {
    @Override
    public void onStart(ISuite suite) {
        System.out.println("Start Suite name: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Stop Suite name: " + suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        System.out.println("onTestStart: " + method.getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        System.out.println("onTestSuccess: " + method.getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        File scrFile = ((TakesScreenshot)WebDriverHolder.INSTANCE.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile((scrFile), new File(("Screen_" + System.currentTimeMillis()+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

//    @Override
//    public void onStart(ISuite suite) {
//        System.out.println("Start Suite name: " + suite.getName());
//    }
//
//    @Override
//    public void onFinish(ISuite suite) {
//        System.out.println("Stop Suite name: " + suite.getName());
//    }
//}
}