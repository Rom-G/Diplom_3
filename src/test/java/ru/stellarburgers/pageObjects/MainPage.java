package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.stellarburgers.locators.MainLocators.MAIN_LOGIN_BUTTON;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    @Step("Open the main page and wait for the login button to load")
    public void openMainPage() {
        driver.get("https://qa-stellarburgers.education-services.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_LOGIN_BUTTON));
    }

    @Step("Click the {name}")
    public void clickElement(By locator, String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
}
