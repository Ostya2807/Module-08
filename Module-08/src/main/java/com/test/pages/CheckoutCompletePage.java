package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage{

    private WebDriver driver;

    @FindBy(className = "complete-header")
    private WebElement completeHeaderText;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCompleteHeaderText(){
        waitForVisibility(completeHeaderText);
        return completeHeaderText.getText();
    }
}
