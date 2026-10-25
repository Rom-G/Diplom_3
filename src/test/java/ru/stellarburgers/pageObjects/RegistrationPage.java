package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ru.stellarburgers.data.UserData.*;

public class RegistrationPage extends BasePage {

    public final By NAME_FIELD =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Имя']/following-sibling::input");

    public final By EMAIL_FIELD =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Email']/following-sibling::input");

    public final By PASSWORD_FIELD =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Пароль']/following-sibling::input");

    public final By REGISTER_BUTTON = By.xpath("//button[normalize-space()='Зарегистрироваться']");
    public final By PASSWORD_ERROR_PARAGRAPH = By.xpath("//p[normalize-space()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filling out the registration form")
    public void fillForm(String password) {
        fillInput(NAME_FIELD, NAME);
        fillInput(EMAIL_FIELD, EMAIL);
        fillInput(PASSWORD_FIELD, password);
    }

    @Step("Find a visible password error and get its text")
    public String findVisiblePasswordErrorAndGetText() {
        return getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR_PARAGRAPH))
                .getText()
                .trim();
    }
}
