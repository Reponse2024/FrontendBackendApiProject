package tests.login;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import constants.AppConstants;
import factories.DriverFactory;
import pages.LoginPage;
import utils.AssertionUtils;

public class LoginTest {

    @Test
    public void validLoginShouldRedirectToHomePage() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);

        AssertionUtils.assertValidLogin(page);

        DriverFactory.closeDriver();
    }
    @Test
    public void invalidLoginShouldShowErrorMessage() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.INVALID_EMAIL, AppConstants.INVALID_PASSWORD);

        AssertionUtils.assertInvalidLogin(page);

        DriverFactory.closeDriver();
    }
    @Test
    public void emptyFieldShouldShowValidationErrors() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLoginPage();
        loginPage.loginWithEmptyFields();

        AssertionUtils.assertInvalidLogin(page);

        DriverFactory.closeDriver();
    }
}
