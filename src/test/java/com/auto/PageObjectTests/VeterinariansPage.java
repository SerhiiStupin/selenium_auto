package com.auto.PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VeterinariansPage{
    private WebDriver driver;
    public VeterinariansPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> veterinariansList(){
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='vets']/tbody/tr"));
        return list;
    }

    public List<String> getVetsList(){
        List<String> vets = new ArrayList<>();

        List<WebElement> vetsList = driver.findElements(By.xpath("//tbody/tr/td[1]"));
        for (WebElement vet : vetsList) {
            vets.add(vet.getText());
        }
        return vets;
    }
//    public List<Veterinarian> getVetsList() {
//        List<Veterinarian> veterinariansList = new ArrayList<>();
//        WebElement vetTable = driver.findElement(By.xpath("//tbody/tr/td[1]"));
//
//        List<WebElement> vetsInList = vetTable.findElements(By.xpath("//tbody/tr"));
//        for (WebElement webElement : vetsInList) {
//            veterinariansList.add(getVet(webElement));
//        }
//        return veterinariansList;
//    }

    public NewVeterPage clickAddBtn(){
        WebElement addVetBtn = driver.findElement(By.xpath("//*[text()='Add Vet']"));
        addVetBtn.click();
        return new NewVeterPage(driver);
    }

    private Veterinarian getVet(WebElement specData){
        Veterinarian veterinarian = new Veterinarian();
        String name = specData.findElement(By.xpath("//tbody/tr/td[1]")).getText();
        String specialties = specData.findElement(By.xpath("//tbody/tr/td[2]")).getText();

        return veterinarian;
    }
    public void homeBtn(){
        WebElement home = driver.findElement(By.xpath("//*[@class='btn btn-default'][text()='Home']"));
        home.click();
    }
}
