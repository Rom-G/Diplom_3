package ru.stellarburgers.locators;

import org.openqa.selenium.By;

public class LoginLocators {

    public static final By LOGIN_REGISTRATION_LINK = By.xpath("//*[@href='/register']");
    public static final By LOGIN_LOGIN_BUTTON = By.xpath("//button[text()='Войти']");

    public static final By LOGIN_EMAIL_FIELD = By.xpath(
            "//label[contains(text(), 'Email')]/ancestor::*[@class='input__container']//input"
    );
    public static final By LOGIN_PASSWORD_FIELD = By.xpath(
            "//label[contains(text(), 'Пароль')]/ancestor::*[@class='input__container']//input"
    );
}
