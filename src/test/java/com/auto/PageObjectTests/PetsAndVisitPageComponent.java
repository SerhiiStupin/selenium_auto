package com.auto.PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PetsAndVisitPageComponent {
    private WebDriver driver;
    public PetsAndVisitPageComponent(WebDriver driver) {
        this.driver = driver;
    }
    String nameIdLocator = "//*[@id='name']";

    private WebElement petParentElement;
    public PetsAndVisitPageComponent(WebDriver driver, WebElement petParentElement) {
        super();
        this.petParentElement = petParentElement;
    }

    public Pet getPet() {
        String name = getInfo("Name").getText();
        String birthDate = getInfo("Birth Date").getText();
        String type = getInfo("Type").getText();
        return new Pet(name, birthDate, type);
    }
    public void nameField(String name){
        WebElement nameField = driver.findElement(By.xpath(nameIdLocator));
        nameField.sendKeys();
    }
    public void calendarClick(){
        WebElement calendar = driver.findElement(By.xpath("//*[@class='mat-datepicker-toggle-default-icon']"));
        calendar.click();
    }
    public void nextMonthArrowClick(){
        WebElement next = driver.findElement(By.xpath("//*[@class='mat-calendar-next-button mat-icon-button']"));
        next.click();
    }
    public void dateSelect(){
        WebElement date = driver.findElement(By.xpath("//*[@class='mat-calendar-body-cell-content'][text()='31']"));
        date.click();
    }
    public void dropDownOpen(){
        WebElement dropDownOpen = driver.findElement(By.xpath("//*[@id='type']"));
        dropDownOpen.click();
    }
    public void typeSelect(){
        WebElement typeSelect = driver.findElement(By.xpath("//div/select/option[last()]"));
        typeSelect.click();
    }
    public void submitBtn(){
        WebElement submitBtn = driver.findElement(By.xpath("//*[@type='submit']"));
        submitBtn.click();
    }
    public String nameCheck(){
        return driver.findElement(By.xpath("//dl/dd[1]")).getText();
    }
    public String petAddingCheck(){
        String petAddingCheck = driver.findElement(By.xpath("//tr[11]/td[last()]/tr[1]")).getText();
        return petAddingCheck;
    }
    public String pageTitle(){
        return driver.findElement(By.xpath("//h2")).getText();
    }
    public String birthDate(){
        String birthDate = driver.findElement(By.xpath("//*[@name='birthDate']")).getText();
        return birthDate;
    }
   // WebElement birthDate = driver.findElement(By.xpath("//*[@name='birthDate']"));

    private WebElement getInfo(String name) {
        return petParentElement.findElement(By.xpath(".//dt[text()='"+name+"']/following-sibling::dd[1]"));
    }
}
