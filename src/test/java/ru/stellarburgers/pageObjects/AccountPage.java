package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private final WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Extract value of {fieldName} field")
    public String extractTextFieldValue(By locator, String fieldName) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return field.getAttribute("value");
    }
}
