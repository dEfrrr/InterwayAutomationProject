package org.example.inventorymanagementbackend.e2e;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class e2eTests {
    public WebDriver driver;
    public TestMethods testMethods;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/");
        testMethods = new TestMethods(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void verifyProductIsAdded() {
        testMethods.addProduct();
        testMethods.searchForProduct();
        WebElement productElement = driver.findElement(Selectors.HomePage.PRODUCT_NAME_LIST);
        assertTrue(productElement.isDisplayed(), "Product is not displayed on the page");
    }

    //FOR THIS TEST, THE PRODUCT LIST SHOULD BE EMPTY TO BEGIN WITH, SO DELETE ANY PRODUCTS ADDED
    @Test
    public void verifyProductIsDeleted(){
        testMethods.addProduct();
        testMethods.deleteProduct();
        WebElement numberOfItems = driver.findElement(Selectors.HomePage.NUMBER_OF_ITEMS);
        String labelText = numberOfItems.getText();
        assertTrue(labelText.contains("0 of 0"), "The number of items is not 0. Actual text: " + labelText);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}