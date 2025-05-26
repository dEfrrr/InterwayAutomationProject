package org.example.inventorymanagementbackend.e2e;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestMethods {

    public WebDriver driver;

    public TestMethods(WebDriver driver){
        this.driver = driver;
    }

    String productName = "Cake";

    public void addProduct(){
        driver.findElement(Selectors.HomePage.ADD_PRODUCT_BUTTON).click();
        driver.findElement(Selectors.AddEditProductPage.ENTER_NAME).sendKeys(productName);
        driver.findElement(Selectors.AddEditProductPage.DESCRIPTION).sendKeys("Medium");
        driver.findElement(Selectors.AddEditProductPage.PRICE).clear();
        driver.findElement(Selectors.AddEditProductPage.PRICE).sendKeys("50");
        driver.findElement(Selectors.AddEditProductPage.QUANTITY).clear();
        driver.findElement(Selectors.AddEditProductPage.QUANTITY).sendKeys("10");
        driver.findElement(Selectors.Category.SELECT_CATEGORY).click();
        doubleClick(Selectors.Category.FOOD_CATEGORY);
        driver.findElement(Selectors.AddEditProductPage.SUBMIT_BUTTON).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
        }
    }

    public void searchForProduct(){
        driver.findElement(Selectors.HomePage.NAME_SEARCH).sendKeys(productName);
        driver.findElement(Selectors.HomePage.FILTER_PRODUCTS_BUTTON).click();
    }

    public void filterPriceToInvalid(){
        driver.findElement(Selectors.HomePage.PRICE_TO).clear();
        driver.findElement(Selectors.HomePage.PRICE_TO).sendKeys("49");
        driver.findElement(Selectors.HomePage.FILTER_PRODUCTS_BUTTON).click();
    }


    public void deleteProduct(){
        driver.findElement(Selectors.HomePage.DELETE_BUTTON).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
        }
    }

    public void doubleClick(By locator){
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
}
