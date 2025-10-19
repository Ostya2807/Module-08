package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutStepTwoPage extends BasePage {
    private WebDriver driver;

    @FindBy(className = "cart_item")
    private List<WebElement> chekcoutItemList;

    @FindBy(id = "finish")
    private WebElement finishButton;



    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToFinishButton(){
        finishButton.click();
    }

    public String getUrlOfThePage(){
        waitForVisibility(finishButton);
        return driver.getCurrentUrl();
    }
}
