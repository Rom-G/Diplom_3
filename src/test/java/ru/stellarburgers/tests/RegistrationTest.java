package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.api.UserAuthedModel;
import ru.stellarburgers.factory.DriverFactory;
import ru.stellarburgers.pageObjects.AccountPage;
import ru.stellarburgers.pageObjects.LoginPage;
import ru.stellarburgers.pageObjects.MainPage;
import ru.stellarburgers.pageObjects.RegistrationPage;

import static org.junit.Assert.assertEquals;
import static ru.stellarburgers.api.UserSteps.deleteUser;
import static ru.stellarburgers.api.UserSteps.loginUser;
import static ru.stellarburgers.data.UserData.*;

public class RegistrationTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that the user can be created")
    @Description("Base test for create user")
    public void registrationSuccessTest() throws InterruptedException {
        WebDriver driver = factory.getDriver();

        MainPage main = new MainPage(driver);
        LoginPage login = new LoginPage(driver);
        RegistrationPage registration = new RegistrationPage(driver);
        AccountPage account = new AccountPage(driver);

        main.openMainPage();
        main.clickElement(main.LOGIN_BUTTON, "login button");
        login.clickElement(login.REGISTRATION_LINK, "registration link");
        registration.fillForm();
        registration.clickElement(registration.REGISTER_BUTTON, "register button");
        login.fillForm();
        login.clickElement(login.LOGIN_BUTTON, "login button");
        main.clickElement(main.ACCOUNT_LINK, "personal account link");

        assertEquals("The data in the Name field and the registration data are different",
                NAME, account.extractTextFieldValue(account.NAME_FIELD, "Name"));
        assertEquals("The data in the Email field and the registration data are different",
                EMAIL, account.extractTextFieldValue(account.EMAIL_FIELD, "Email"));
    }

    @After
    public void tearDown() {
        UserAuthedModel userAuthed = loginUser(EMAIL, PASSWORD).as(UserAuthedModel.class);

        if (userAuthed.isSuccess()) {
            deleteUser(userAuthed.getAccessToken());
        }
    }
}
