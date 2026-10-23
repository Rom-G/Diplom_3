package ru.stellarburgers.locators;

import org.openqa.selenium.By;

public class RegistrationLocators {

    public static final By REGISTRATION_NAME_FIELD = By.xpath(
            "//label[contains(text(), 'Имя')]/ancestor::*[@class='input__container']//input"
    );
    public static final By REGISTRATION_EMAIL_FIELD = By.xpath(
            "//label[contains(text(), 'Email')]/ancestor::*[@class='input__container']//input"
    );
    public static final By REGISTRATION_PASSWORD_FIELD = By.xpath(
            "//label[contains(text(), 'Пароль')]/ancestor::*[@class='input__container']//input"
    );
    public static final By REGISTRATION_REGISTER_BUTTON = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
}
