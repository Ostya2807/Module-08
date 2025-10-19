package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepOnePage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "first-name")
    private WebElement firstNameInputField;

    @FindBy(id = "last-name")
    private WebElement lastNameInputField;
    @FindBy(id = "postal-code")
    private WebElement zipCodeInputField;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToContinueButton() {
        waitForVisibility(continueButton);
        continueButton.click();
    }

    public String getErrorMessage() {
        waitForVisibility(errorMessage);
        return errorMessage.getText();
    }
    public void fillOnlyFirstName(String firstName) {
        waitForVisibility(firstNameInputField);
        waitForVisibility(continueButton);
        firstNameInputField.clear();
        firstNameInputField.sendKeys(firstName);
        continueButton.click();
    }

    public void fillFirstAndLastName(String firstName, String lastName) {
        firstNameInputField.clear();
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.clear();
        lastNameInputField.sendKeys(lastName);
        continueButton.click();
    }

    public void fillAllCheckoutFields(String firstName, String lastName, String postalCode) {
        firstNameInputField.clear();
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.clear();
        lastNameInputField.sendKeys(lastName);
        zipCodeInputField.clear();
        zipCodeInputField.sendKeys(postalCode);
        continueButton.click();
    }

    public void waitToLoadTheURL(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-two.html"));
    }
}
