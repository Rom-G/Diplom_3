package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.data.UserData.*;
import static ru.stellarburgers.locators.LoginLocators.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filling out the login form")
    public void fillForm() {
        fillInput(LOGIN_EMAIL_FIELD, EMAIL);
        fillInput(LOGIN_PASSWORD_FIELD, PASSWORD);
    }
}
