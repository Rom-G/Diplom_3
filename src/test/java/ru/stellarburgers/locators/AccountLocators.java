package ru.stellarburgers.locators;

import org.openqa.selenium.By;

public class AccountLocators {

    public static final By ACCOUNT_NAME_FIELD = By.xpath(
            "//label[contains(text(), 'Имя')]/ancestor::*[@class='input__container']//input"
    );
    public static final By ACCOUNT_EMAIL_FIELD = By.xpath(
            "//label[contains(text(), 'Email')]/ancestor::*[@class='input__container']//input"
    );
}
