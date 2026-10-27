package ru.stellarburgers.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private final By NAME_FIELD =
            By.xpath("//ul[contains(@class, 'Profile_profileList')]" +
                    "//label[normalize-space()='Имя']/following-sibling::input");

    private final By EMAIL_FIELD =
            By.xpath("//ul[contains(@class, 'Profile_profileList')]" +
                    "//label[normalize-space()='Логин']/following-sibling::input");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Extract value of Name field")
    public String extractNameFieldValue() {
        return extractFieldValue(NAME_FIELD);
    }

    @Step("Extract value of Email field")
    public String extractEmailFieldValue() {
        return extractFieldValue(EMAIL_FIELD);
    }
}
