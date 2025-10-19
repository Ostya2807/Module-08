package com.saucetests.tests;

import com.test.pages.CartPage;
import com.test.pages.CheckoutStepOnePage;
import com.test.pages.InventoryPage;
import com.test.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutStepOnePageTest {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
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
    public void loadApplication(){
        driver.get(URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
    }

    @Test
    public void shouldShowErrorWhenOnlyFirstNameIsFilled(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemsToCart();
        inventoryPage.openCartPageWithShoppingCartIcon();
        cartPage.clickToCheckout();
        checkoutStepOnePage.fillOnlyFirstName("Vass");
        checkoutStepOnePage.clickToContinueButton();
        Assert.assertEquals( checkoutStepOnePage.getErrorMessage(),"Error: Last Name is required");
    }

    @Test
    public void shouldShowErrorWhenFirstNameAndLastIsFilled(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemsToCart();
        inventoryPage.openCartPageWithShoppingCartIcon();
        cartPage.clickToCheckout();
        checkoutStepOnePage.fillFirstAndLastName("Vass", "Jenő");
        checkoutStepOnePage.clickToContinueButton();
        Assert.assertEquals( checkoutStepOnePage.getErrorMessage(),"Error: Postal Code is required");
    }

    @Test
    public void continueCheckoutWhenFormIsFilledCorrectly(){
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemsToCart();
        inventoryPage.openCartPageWithShoppingCartIcon();
        cartPage.clickToCheckout();
        checkoutStepOnePage.fillAllCheckoutFields("Vass", "Jennő", "1032");
        checkoutStepOnePage.waitToLoadTheURL();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
    }
}
