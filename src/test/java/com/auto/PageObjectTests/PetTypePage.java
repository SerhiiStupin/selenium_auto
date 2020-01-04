package com.auto.PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PetTypePage {
    private WebDriver driver;

    public PetTypePage(WebDriver driver) {
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
//    public Pet pets (WebElement petType){
//        Pet pet = new Pet();
//        WebElement name = petType.findElement(By.xpath("//tr/td[1]"));
//        return pet;
//    }
//    public List<Pet> petList(){
//        List<Pet> petList = new ArrayList<>();
//        WebElement all = driver.findElement(By.xpath("//tr[1]/td/input"));
//
//        List<WebElement> allPets = driver.findElements(By.xpath("//tr/td[1]"));
//        for (WebElement types : allPets) {
//            petList.add(pets(types));
//        }
//        return petList();
//    }
    public String typeList() {
        return driver.findElement(By.xpath("//tr[last()]/td/input")).getAttribute("ng-reflect-model");
    }

    String petsList = "//tbody/tr";
}
