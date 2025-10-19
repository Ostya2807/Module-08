package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {

    private WebDriver driver;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement bikeLightAddToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement fleeceJacketAddToCartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    private LoginPage loginPage;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, this);
    }

    public void addOneItemToCart() {
        waitForVisibility(bikeLightAddToCartButton);
        bikeLightAddToCartButton.click();
    }

    public void addItemsToCart() {
        waitForVisibility(bikeLightAddToCartButton);
        waitForVisibility(fleeceJacketAddToCartButton);
        bikeLightAddToCartButton.click();
        fleeceJacketAddToCartButton.click();
    }

    public int getNumberOfCartItems() {
        return Integer.parseInt(shoppingCartBadge.getText());
    }

    public void openCartPageWithShoppingCartIcon() {
        waitForVisibility(shoppingCartBadge);
        shoppingCartBadge.click();
    }

}
