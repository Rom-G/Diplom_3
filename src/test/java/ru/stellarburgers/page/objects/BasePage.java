package ru.stellarburgers.page.objects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step("Enter {value} in the field")
    public void fillInput(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        field.clear();
        field.sendKeys(value);
    }

    public String extractFieldValue(By locator) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return field.getAttribute("value");
    }
}
