package org.example.inventorymanagementbackend.e2e;
import org.openqa.selenium.By;

public class Selectors {


    public static class Category {
        public static final By SELECT_CATEGORY = By.className("mat-mdc-select-value");
        public static final By FOOD_CATEGORY = By.xpath("//span[contains(text(),'Food')]");
        public static final By HOUSEHOLD_CATEGORY = By.xpath("//span[contains(text(),'Household')]");
        public static final By PERSONAL_CARE_CATEGORY = By.xpath("//span[contains(text(),'Personal Care')]");
        public static final By SPORTS_CATEGORY = By.xpath("//span[contains(text(),'Sports')]");
        public static final By PET_SUPPLIES_CATEGORY = By.xpath("//span[contains(text(),'Pet supplies')]");
    }

    public static class HomePage {
        public static final String productName = "Cake";
        public static final By ADD_PRODUCT_BUTTON = By.xpath("//button[text()='Add product']");
        public static final By DETAILS_BUTTON = By.xpath("//button[text()='Details']");
        public static final By EDIT_BUTTON = By.xpath("//span[contains(text(),'Edit')]");
        public static final By DELETE_BUTTON = By.xpath("//span[contains(text(),'Delete')]");
        public static final By HOME_BUTTON = By.xpath("//span[contains(text(),'home')]");
        public static final By NAME_SEARCH = By.cssSelector("[formcontrolname='name']");
        public static final By PRICE_FROM = By.cssSelector("[formcontrolname='priceFrom']");
        public static final By PRICE_TO = By.cssSelector("[formcontrolname='priceTo']");
        public static final By FILTER_PRODUCTS_BUTTON = By.xpath("//button[text()='Filter products']");
        public static final By ITEMS_PER_PAGE_BUTTON = By.className("mat-mdc-paginator-touch-target");
        public static final By FIVE_ITEMS_PER_PAGE = By.xpath("//span[contains(text(),'Household')]");
        public static final By TEN_ITEMS_PER_PAGE = By.xpath("//span[contains(text(),'10')]");
        public static final By TWENTY_ITEMS_PER_PAGE = By.xpath("//span[contains(text(),'Household')]");
        public static final By FIFTY_ITEMS_PER_PAGE = By.xpath("//span[contains(text(),'Household')]");
        public static final By ONEHUNDRED_ITEMS_PER_PAGE = By.xpath("//span[contains(text(),'Household')]");
        public static final By PRODUCT_NAME_LIST = By.xpath("//td[contains(text(), '" + productName + "')]");
        public static final By NUMBER_OF_ITEMS = By.className("mat-mdc-paginator-range-label");
    }

    public static class AddEditProductPage {
        public static final By ENTER_NAME = By.cssSelector("[formcontrolname='name']");
        public static final By DESCRIPTION = By.cssSelector("[formcontrolname='description']");
        public static final By PRICE = By.cssSelector("[formcontrolname='price']");
        public static final By QUANTITY = By.cssSelector("[formcontrolname='quantityInStock']");
        public static final By SUBMIT_BUTTON = By.xpath("//button[text()='Submit']");
        public static final By BACK_BUTTON = By.xpath("//button[text()='Back']");
    }
}