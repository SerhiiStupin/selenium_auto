package com.auto.PageObjectTests;

import com.auto.TestPreconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

public class OwnersPage extends TestPreconditions {
    private WebDriver driver;

    public OwnersPage(WebDriver driver) {
        this.driver = driver;
    }

    public OwnersPage openPage() {
        goToUrl("/owners", "Owners");
        return this;
    }

    public List<String> getOwnersNames() {
        List<String> owners = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.cssSelector(".ownerFullName>a"));
        for (WebElement fullName : elements) {
            owners.add(fullName.getText());
        }

        return owners;
    }

    public List<Owner> getOwnersList() {
        List<Owner> owners = new ArrayList<>();
        WebElement ownersTable = driver.findElement(By.xpath("//*[@class='table-responsive']"));

        List<WebElement> ownersList = ownersTable.findElements(By.xpath(".//tbody/tr"));
        for (WebElement userRow : ownersList) {
            owners.add(createOwner(userRow));
        }
        return owners;
    }

//    public NewOwnerPage clickAddOwnerBtn() {
//        WebElement addOwnerBtn = driver.findElement(By.xpath("//*[text()='Add Owner']"));
//        addOwnerBtn.click();
//        return new NewOwnerPage(driver);
//    }

    public NewOwnerPage clickAddOwnerBtn() {
        WebElement addOwnerBtn = driver.findElement(By.xpath("//*[text()='Add Owner']"));
        addOwnerBtn.click();
        return new NewOwnerPage(driver);
    }

    private Owner createOwner(WebElement userRow) {
        com.auto.PageObjectTests.Owner owner = new com.auto.PageObjectTests.Owner();
        String fullName = userRow.findElement(By.xpath("./td/a")).getText();
        String[] fullNameArray = fullName.split(" ");
        if (fullNameArray.length > 1) {
            owner.setFirstName(fullNameArray[0]);
            owner.setLastName(fullNameArray[1]);
        } else {
            owner.setFirstName(fullNameArray[0]);
        }
        owner.setAddress(userRow.findElement(By.xpath("./td[2]")).getText());
        owner.setCity(userRow.findElement(By.xpath("./td[3]")).getText());
        owner.setTelephone(userRow.findElement(By.xpath("./td[4]")).getText());

        String pets = userRow.findElement(By.xpath("./td[5]")).getText();
        if(!pets.isEmpty()) {
            owner.setPets(pets);
        }

        return (Owner) owner;
    }
    public List<WebElement> ownersList(){
        return driver.findElements(By.cssSelector("td.ownerFullName"));
    }
}
