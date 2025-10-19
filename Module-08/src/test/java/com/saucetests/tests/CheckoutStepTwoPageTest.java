package com.saucetests.tests;

import com.test.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutStepTwoPageTest {

    private WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    private final String URL = "https://www.saucedemo.com/";


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void loadApplication() {
        driver.get(URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
    }

    @Test
    public void checkTheCurrentUrl(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemsToCart();
        inventoryPage.openCartPageWithShoppingCartIcon();
        cartPage.clickToCheckout();
        checkoutStepOnePage.fillAllCheckoutFields("Vass", "Jennő", "1032");
        Assert.assertEquals(checkoutStepTwoPage.getUrlOfThePage(), "https://www.saucedemo.com/checkout-step-two.html");
    }

}
