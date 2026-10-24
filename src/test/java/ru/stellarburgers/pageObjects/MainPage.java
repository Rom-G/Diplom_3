package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ru.stellarburgers.locators.MainLocators.MAIN_LOGIN_BUTTON;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the main page and wait for the login button to load")
    public void openMainPage() {
        getDriver().get("https://qa-stellarburgers.education-services.ru/");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(MAIN_LOGIN_BUTTON));
    }
}
