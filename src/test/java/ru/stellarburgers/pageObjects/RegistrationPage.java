package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.data.UserData.*;

public class RegistrationPage extends BasePage {

    public final By REGISTRATION_NAME_FIELD = By.xpath(
            "//label[contains(text(), 'Имя')]/ancestor::*[@class='input__container']//input");
    public final By REGISTRATION_EMAIL_FIELD = By.xpath(
            "//label[contains(text(), 'Email')]/ancestor::*[@class='input__container']//input");
    public final By REGISTRATION_PASSWORD_FIELD = By.xpath(
            "//label[contains(text(), 'Пароль')]/ancestor::*[@class='input__container']//input");
    public final By REGISTRATION_REGISTER_BUTTON = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");

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
