package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    public final By LOGIN_BUTTON = By.xpath("//button[contains(text(), 'Войти')]");
    public final By ACCOUNT_LINK = By.cssSelector("a[href='/account']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the main page and wait for the login button to load")
    public void openMainPage() {
        getDriver().get("https://qa-stellarburgers.education-services.ru/");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }
}
