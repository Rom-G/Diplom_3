package ru.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.api.UserAuthedModel;
import ru.stellarburgers.data.LoginFlowType;
import ru.stellarburgers.factory.DriverFactory;
import ru.stellarburgers.pageObjects.*;

import static org.junit.Assert.assertEquals;
import static ru.stellarburgers.api.UserSteps.*;
import static ru.stellarburgers.data.UserData.*;

@RunWith(Parameterized.class)
public class LoginFlowTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final LoginFlowType flowType;
    private final String allureDescription;

    public LoginFlowTest(LoginFlowType flowType, String allureDescription) {
        this.flowType = flowType;
        this.allureDescription = allureDescription;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { LoginFlowType.VIA_LOGIN_BTN, "Path 1: through the \"Login to account\" button" },
                { LoginFlowType.VIA_PERSONAL_CABINET, "Path 2: through the \"Personal Account\"" },
                { LoginFlowType.VIA_REGISTRATION_FORM, "Path 3: from the registration form" },
                { LoginFlowType.VIA_RESET_PASSWORD_FORM, "Path 4: from the password recovery form" }
        };
    }

    @Before
    public void setUp() {
        registerUser(EMAIL, PASSWORD, NAME);
    }

    @Test
    @DisplayName("Check the different entry paths")
    public void loginPathTest() {
        Allure.description(allureDescription);

        WebDriver driver = factory.getDriver();
        MainPage main = new MainPage(driver);
        LoginPage login = new LoginPage(driver);
        AccountPage account = new AccountPage(driver);

        switch (flowType) {
            case VIA_LOGIN_BTN:
                main.open();
                main.clickLoginButton();
                break;
            case VIA_PERSONAL_CABINET:
                main.open();
                main.clickPersonalAccountLink();
                break;
            case VIA_REGISTRATION_FORM:
                RegistrationPage registration = new RegistrationPage(driver);
                registration.open();
                registration.clickLoginLink();
                break;
            case VIA_RESET_PASSWORD_FORM:
                RecoveryPage recovery = new RecoveryPage(driver);
                recovery.open();
                recovery.clickLoginLink();
                break;
        }

        login.fillForm();
        login.clickLoginButton();
        main.clickPersonalAccountLink();

        assertEquals("The data in the Name field and the login data are different",
                NAME, account.extractNameFieldValue());
        assertEquals("The data in the Email field and the login data are different",
                EMAIL, account.extractEmailFieldValue());
    }

    @After
    public void tearDown() {
        UserAuthedModel userAuthed = loginUser(EMAIL, PASSWORD).as(UserAuthedModel.class);

        if (userAuthed.isSuccess()) {
            deleteUser(userAuthed.getAccessToken());
        }
    }
}
