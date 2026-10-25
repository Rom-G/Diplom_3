package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.data.UserData.*;

public class LoginPage extends BasePage {

    public final By REGISTRATION_LINK = By.xpath("//*[@href='/register']");
    public final By LOGIN_BUTTON = By.xpath("//button[normalize-space()='Войти']");
    public final By EMAIL_FIELD =
            By.xpath("//h2[normalize-space()='Вход']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Email']/following-sibling::input");

    public final By PASSWORD_FIELD =
            By.xpath("//h2[normalize-space()='Вход']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Пароль']/following-sibling::input");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filling out the login form")
    public void fillForm() {
        fillInput(EMAIL_FIELD, EMAIL);
        fillInput(PASSWORD_FIELD, PASSWORD);
    }
}
