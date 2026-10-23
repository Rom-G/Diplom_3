package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.stellarburgers.locators.LoginLocators.LOGIN_REGISTRATION_LINK;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Click the registration link")
    public void clickRegistrationLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_REGISTRATION_LINK));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", link);
        link.click();
    }
}
