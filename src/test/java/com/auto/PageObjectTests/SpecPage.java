package com.auto.PageObjectTests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpecPage {
    private WebDriver driver;

    public SpecPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setName (String name){
        WebElement nameField = driver.findElement(By.xpath("//*[@id='name']"));
        nameField.clear();
        nameField.sendKeys(name);
    }
    public void saveBtn(){
        WebElement save = driver.findElement(By.xpath("//*[text()='Save']"));
        save.click();
    }
    public void addBtn(){
        WebElement add = driver.findElement(By.xpath("//div/button[2]"));
        add.click();
    }
    public void homeBtn(){
        WebElement home = driver.findElement(By.xpath("//div/div/button[1]"));
        home.click();
    }
    public void deleteLast(){
        WebElement delete = driver.findElement(By.xpath("//tbody/tr[last()]/td/button[text()='Delete']"));
        delete.click();
    }
    public String specList() {
        return driver.findElement(By.xpath("//tr[last()]/td/input")).getAttribute("ng-reflect-model");
    }
    String specRow = "//tbody/tr";

}
