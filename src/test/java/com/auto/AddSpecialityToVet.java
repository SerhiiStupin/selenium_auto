package com.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddSpecialityToVet extends TestPreconditions {
    @Test
    public void addProfessionToVet() {
        String vetProfession = "Surgeon";
        goToHomePage();
        navigateToAddVetUsingMenu();
        driver.findElement(By.xpath(firstNameLocator)).sendKeys("Doctor");
        driver.findElement(By.xpath(lastNameLocator)).sendKeys("House");
        saveVetButtonClick();
        driver.findElement(By.xpath("//tr[last()]/td/button[text()='Edit Vet']")).click();
        waiting().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")));
        driver.findElement(By.xpath("//*[@class='mat-select-arrow']")).click();
        driver.findElement(By.xpath("//div/mat-option[3]")).click();
        WebElement elemClick = driver.findElement(By.xpath("//*[@type='submit']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", elemClick);
        waiting().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add Vet']")));
        String currentProf = driver.findElement(By.xpath("//tr[last()]/td[2]/div")).getText();
        assertEquals(vetProfession, currentProf);
    }
    String firstNameLocator = "//*[@id='firstName']";
    String lastNameLocator = "//*[@id='lastName']";

    public void navigateToAddVetUsingMenu(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][text()='Veterinarians']")).click();
        driver.findElement(By.xpath("//a[@routerlink='/vets/add']")).click();
    }
    public void saveVetButtonClick(){
        driver.findElement(By.xpath("//*[text()='Save Vet']")).click();
    }
}
