package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.data.UserData.*;
import static ru.stellarburgers.locators.RegistrationLocators.*;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filling out the registration form")
    public void fillForm() {
        fillInput(REGISTRATION_NAME_FIELD, NAME);
        fillInput(REGISTRATION_EMAIL_FIELD, EMAIL);
        fillInput(REGISTRATION_PASSWORD_FIELD, PASSWORD);
    }
}
