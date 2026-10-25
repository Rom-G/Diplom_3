package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
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

    private MainPage main;
    private LoginPage login;
    private RegistrationPage registration;
    private AccountPage account;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Before
    public void setUp() {
        WebDriver driver = factory.getDriver();

        main = new MainPage(driver);
        login = new LoginPage(driver);
        registration = new RegistrationPage(driver);
        account = new AccountPage(driver);
    }

    @Test
    @DisplayName("Check that the user can be created")
    @Description("Base test for create user")
    public void registrationSuccessTest() {
        main.openMainPage();
        main.clickElement(main.LOGIN_BUTTON, "login button");
        login.clickElement(login.REGISTRATION_LINK, "registration link");
        registration.fillForm(PASSWORD);
        registration.clickElement(registration.REGISTER_BUTTON, "register button");
        login.fillForm();
        login.clickElement(login.LOGIN_BUTTON, "login button");
        main.clickElement(main.ACCOUNT_LINK, "personal account link");

        assertEquals("The data in the Name field and the registration data are different",
                NAME, account.extractTextFieldValue(account.NAME_FIELD, "Name"));
        assertEquals("The data in the Email field and the registration data are different",
                EMAIL, account.extractTextFieldValue(account.EMAIL_FIELD, "Email"));
    }

    @Test
    @DisplayName("Check the password length is less than 6 characters invalid")
    @Description("The test verifies that it is impossible to create a user with an invalid password")
    public void registrationWithPasswordLessThan6CharsFails() {
        main.openMainPage();
        main.clickElement(main.LOGIN_BUTTON, "login button");
        login.clickElement(login.REGISTRATION_LINK, "registration link");
        registration.fillForm(INVALID_PASSWORD);
        registration.clickElement(registration.REGISTER_BUTTON, "register button");
    }

    @After
    public void tearDown() {
        UserAuthedModel userAuthed = loginUser(EMAIL, PASSWORD).as(UserAuthedModel.class);

        if (userAuthed.isSuccess()) {
            deleteUser(userAuthed.getAccessToken());
        }
    }
}
