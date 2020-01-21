package com.auto.PageObjectTests;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SpecPage {
    private WebDriver driver;
    public SpecPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Getting a list of specialties")
    public List<WebElement> specialists(){
        return driver.findElements(By.xpath("//tbody/tr"));
    }
    @Step("Setting a spacielty name")
    public void setName (String name){
        WebElement nameField = driver.findElement(By.xpath("//*[@id='name']"));
        nameField.clear();
        nameField.sendKeys(name);
    }
    @Step("Save button click")
    public void saveBtn(){
        WebElement save = driver.findElement(By.xpath("//*[text()='Save']"));
        save.click();
    }
    @Step("Add button click")
    public void addBtn(){
        WebElement add = driver.findElement(By.xpath("//div/button[2]"));
        add.click();
    }
    @Step("Home button click")
    public void homeBtn(){
        WebElement home = driver.findElement(By.xpath("//div/div/button[1]"));
        home.click();
    }
    @Step("Deleting of the last item")
    public void deleteLast(){
        WebElement delete = driver.findElement(By.xpath("//tbody/tr[last()]/td/button[text()='Delete']"));
        delete.click();
    }
    @Step("Getting the specialty name")
    public String specList() {
        return driver.findElement(By.xpath("//tr[last()]/td/input")).getAttribute("ng-reflect-model");
    }
}
