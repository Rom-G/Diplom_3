package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.stellarburgers.data.UserData.*;
import static ru.stellarburgers.locators.RegistrationLocators.*;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Filling out the registration form")
    public void fillForm() {
        fillInput(REGISTRATION_NAME_FIELD, NAME);
        fillInput(REGISTRATION_EMAIL_FIELD, EMAIL);
        fillInput(REGISTRATION_PASSWORD_FIELD, PASSWORD);
    }

    @Step("Enter {value} in the field")
    private void fillInput(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(REGISTRATION_REGISTER_BUTTON));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }
}
