package ru.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.factory.DriverFactory;
import ru.stellarburgers.pageObjects.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

@RunWith(Parameterized.class)
public class IngredientTransitionTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String categoryText;

    public IngredientTransitionTest(String categoryText) {
        this.categoryText = categoryText;
    }

    @Parameterized.Parameters
    public static Object[][] getCategory() {
        return new Object[][] {
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        };
    }

    @Test
    @DisplayName("Check the scroll after clicking on tab")
    public void scrollByTabSuccessTest() {
        String description = "Scroll by clicking on the " + categoryText + " tab";
        Allure.description(description);

        WebDriver driver = factory.getDriver();

        MainPage main = new MainPage(driver);
        main.open();
        main.clickTabWithText(categoryText);
        int actualOffset = main.countOffsetFromContainerTop(categoryText);

        assertThat("The offset of the header from the top is more than expected",
                actualOffset, lessThanOrEqualTo(2));
    }
}
