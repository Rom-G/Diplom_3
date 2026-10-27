package ru.stellarburgers.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {

    private final By LOGIN_BUTTON = By.xpath("//button[contains(text(), 'Войти')]");
    private final By ACCOUNT_LINK = By.cssSelector("a[href='/account']");
    private final By INGREDIENT_CONTAINER =
            By.xpath(
                    "//h2[normalize-space()='Булки']" +
                    "/ancestor::div[contains(@class, 'menuContainer')]");

    private final By INGREDIENTS_HEADERS = By.cssSelector("h2.text.text_type_main-medium");

    private final String TAB_LOCATOR_TEMPLATE = "//span[normalize-space()='%s']/ancestor::div[contains(@class, 'tab_tab')]";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the main page and wait for the login button to load")
    public void open() {
        getDriver().get("https://qa-stellarburgers.education-services.ru/");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    public void clickLoginButton() {
        clickElement(LOGIN_BUTTON, "login button");
    }

    public void clickPersonalAccountLink() {
        clickElement(ACCOUNT_LINK, "personal account link");
    }

    @Step("Click on the {tabText} tab by JavascriptExecutor")
    public void clickTabWithText(String tabText) {
        By tabLocator = getTabByText(tabText);
        WebElement tab = getWait().until(ExpectedConditions.elementToBeClickable(tabLocator));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", tab);
    }

    @Step("Get a tab by its name")
    private By getTabByText(String tabName) {
        String xpath = String.format(TAB_LOCATOR_TEMPLATE, tabName);
        return By.xpath(xpath);
    }

    @Step("Count the distance from the header {headerText} to the top of the container")
    public int countOffsetFromContainerTop(String headerText) {
        WebElement container = getDriver().findElement(INGREDIENT_CONTAINER);
        WebElement header = waitAndGetIngredientsHeader(headerText);
        waitIngredientsScrollFinish(container, header);

        int containerTop = getUpperYCoordinateOfElement(container, "ingredient's container");
        int headerTop = getUpperYCoordinateOfElement(header, "ingredient's header");

        return headerTop - containerTop;
    }

    @Step("Wait and get {headerText} header from container")
    private WebElement waitAndGetIngredientsHeader(String headerText) {
        return getWait().until(driver -> {
            List<WebElement> candidates = driver.findElements(INGREDIENTS_HEADERS);
            for (WebElement h : candidates) {
                if (h.isDisplayed() && h.getText().trim().contains(headerText)) {
                    return h;
                }
            }
            return null;
        });
    }

    @Step("Waiting for the ingredient's header to scroll to the top of the container")
    private void waitIngredientsScrollFinish(WebElement container, WebElement header) {
        getWait().until(d -> {
            int containerTop = getUpperYCoordinateOfElement(container, "ingredient's container");
            int headerTop = getUpperYCoordinateOfElement(header, "ingredient's header");

            int offsetFromContainerTop = headerTop - containerTop;
            return offsetFromContainerTop <= 2;
        });
    }

    @Step("Get the upper Y coordinate of an {elementName}")
    private int getUpperYCoordinateOfElement(WebElement element, String elementName) {
        Object result = ((JavascriptExecutor) getDriver()).executeScript(
                "return arguments[0].getBoundingClientRect().top;", element);

        if (result instanceof Double) {
            return ((Double) result).intValue();
        } else if (result instanceof Number) {
            return ((Number) result).intValue();
        } else {
            throw new IllegalArgumentException("Unexpected type returned from script: " + result.getClass());
        }
    }
}
