package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.api.UserAuthedModel;
import ru.stellarburgers.factory.DriverFactory;

import static ru.stellarburgers.api.UserSteps.deleteUser;
import static ru.stellarburgers.api.UserSteps.loginUser;
import static ru.stellarburgers.data.UserData.EMAIL;
import static ru.stellarburgers.data.UserData.PASSWORD;

public class RegistrationTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that the user can be created")
    @Description("base test for create user")
    public void registrationSuccessTest() {
        WebDriver driver = factory.getDriver();

    }

    @After
    public void tearDown() {
        UserAuthedModel userAuthed = loginUser(EMAIL, PASSWORD).as(UserAuthedModel.class);

        if (userAuthed.isSuccess()) {
            deleteUser(userAuthed.getAccessToken());
        }
    }
}
