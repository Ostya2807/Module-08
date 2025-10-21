package com.saucetests.tests;

import com.test.pages.CartPage;
import com.test.pages.InventoryPage;
import com.test.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    private final String URL = "https://www.saucedemo.com/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void loadApplication(){
        driver.get(URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void checkSizeOfTheCart(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemsToCart();
        inventoryPage.openCartPageWithShoppingCartIcon();
        Assert.assertEquals(2, cartPage.getSizeOfCart());
    }
}
