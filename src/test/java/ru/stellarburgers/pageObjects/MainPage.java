package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private final By LOGIN_BUTTON = By.xpath("//button[contains(text(), 'Войти')]");
    private final By ACCOUNT_LINK = By.cssSelector("a[href='/account']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the main page and wait for the login button to load")
    public void open() {
        getDriver().get("https://qa-stellarburgers.education-services.ru/");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    public void clickLoginButton() {
        clickElement(LOGIN_BUTTON, "login button");
    }

    public void clickPersonalAccountLink() {
        clickElement(ACCOUNT_LINK, "personal account link");
    }
}
