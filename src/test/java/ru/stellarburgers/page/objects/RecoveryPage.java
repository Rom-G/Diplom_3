package ru.stellarburgers.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecoveryPage  extends BasePage {

    private final By RECOVERY_BUTTON = By.xpath("//button[normalize-space()='Восстановить']");
    private final By LOGIN_LINK =
            By.xpath("//h2[normalize-space()='Восстановление пароля']" +
                    "/ancestor::div[contains(@class,'Auth_login')]//a[@href='/login']");

    public RecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the recovery page and wait for the recovery button to load")
    public void open() {
        getDriver().get("https://qa-stellarburgers.education-services.ru/forgot-password");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(RECOVERY_BUTTON));
    }

    public void clickLoginLink() {
        clickElement(LOGIN_LINK, "login link");
    }
}
