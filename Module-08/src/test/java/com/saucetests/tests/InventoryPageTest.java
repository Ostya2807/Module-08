package com.saucetests.tests;

import com.test.pages.InventoryPage;
import com.test.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class InventoryPageTest {
    private WebDriver driver;
    protected LoginPage loginPage;

    protected InventoryPage inventoryPage;

    private final String URL = "https://www.saucedemo.com/";

    @BeforeMethod
    public void loadApplication(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void addOneItemToCartTest(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addOneItemToCart();
        Assert.assertEquals(inventoryPage.getNumberOfCartItems(), 1);
    }

    @Test
    public void addMoreItemsToCartTest(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemsToCart();
        Assert.assertEquals(inventoryPage.getNumberOfCartItems(), 2);
    }
}
