package ru.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ru.stellarburgers.data.UserData.*;

public class RegistrationPage extends BasePage {

    private final By NAME_FIELD =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Имя']/following-sibling::input");

    private final By EMAIL_FIELD =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Email']/following-sibling::input");

    private final By PASSWORD_FIELD =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]" +
                    "//label[normalize-space()='Пароль']/following-sibling::input");

    private final By LOGIN_LINK =
            By.xpath("//h2[normalize-space()='Регистрация']" +
                    "/ancestor::div[contains(@class,'Auth_login')]//a[@href='/login']");

    private final By REGISTER_BUTTON = By.xpath("//button[normalize-space()='Зарегистрироваться']");
    private final By PASSWORD_ERROR_PARAGRAPH = By.xpath("//p[normalize-space()='Некорректный пароль']");

    public static final String PASSWORD_ERROR_TEXT = "Некорректный пароль";

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the registration page and wait for the register button to load")
    public void open() {
        getDriver().get("https://qa-stellarburgers.education-services.ru/register");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
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

    public void clickRegisterButton() {
        clickElement(REGISTER_BUTTON, "register button");
    }

    public void clickLoginLink() {
        clickElement(LOGIN_LINK, "login link");
    }
}
