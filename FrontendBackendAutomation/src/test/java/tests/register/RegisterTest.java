package tests.register;

import base.BasePage;
import com.microsoft.playwright.Page;
import constants.RegisterConstants;
import factories.DriverFactory;
import org.testng.annotations.Test;
import pages.RegisterPage;
import constants.RegisterTestData;
import utils.AssertionUtils;
import utils.WaitUtils;

public class RegisterTest {

    @Test
    public void testValidRegistration() {
        Page page = DriverFactory.initDriver();

        BasePage basePage = new BasePage(page);
        basePage.clickCreateAccountButton();

        RegisterPage registerPage = new RegisterPage(page);
        registerPage.fillRegisterForm(
                RegisterTestData.FIRST_NAME,
                RegisterTestData.LAST_NAME,
                RegisterTestData.EMAIL,
                RegisterTestData.PASSWORD
        );
        registerPage.submitRegistration();

        AssertionUtils.assertRegistrationSuccess(page);
        DriverFactory.closeDriver();
    }

    @Test
    public void testInvalidEmailRegistration() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        basePage.clickCreateAccountButton();

        RegisterPage registerPage = new RegisterPage(page);
        registerPage.fillRegisterForm(
                RegisterTestData.FIRST_NAME,
                RegisterTestData.LAST_NAME,
                RegisterTestData.INVALID_EMAIL,
                RegisterTestData.PASSWORD
        );
        registerPage.submitRegistration();

        AssertionUtils.assertInvalidEmail(page);
        DriverFactory.closeDriver();
    }

    @Test
    public void testWeakPasswordRegistration() {
        Page page = DriverFactory.initDriver();

        BasePage basePage = new BasePage(page);
        basePage.clickCreateAccountButton();

        RegisterPage registerPage = new RegisterPage(page);
        registerPage.fillRegisterForm(
                RegisterTestData.FIRST_NAME,
                RegisterTestData.LAST_NAME,
                RegisterTestData.EMAIL,
                RegisterTestData.WEAK_PASSWORD
        );
        registerPage.submitRegistration();

        AssertionUtils.assertPasswordError(page);
        DriverFactory.closeDriver();
    }

    @Test
    public void testEmptyFieldsRegistration() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        basePage.clickCreateAccountButton();

        RegisterPage registerPage = new RegisterPage(page);
        registerPage.fillRegisterForm(
                "",
                RegisterTestData.LAST_NAME,
                RegisterTestData.EMAIL,
                RegisterTestData.PASSWORD
        );
        registerPage.submitRegistration();

        AssertionUtils.assertEmptyField(page, RegisterConstants.FIRST_NAME_INPUT);
        DriverFactory.closeDriver();
    }
    @Test
    public void testDuplicateEmailRegistration() {
        Page page = DriverFactory.initDriver();

        BasePage basePage = new BasePage(page);
        basePage.clickCreateAccountButton();

        RegisterPage registerPage = new RegisterPage(page);
        registerPage.fillRegisterForm(
                RegisterTestData.FIRST_NAME,
                RegisterTestData.LAST_NAME,
                RegisterTestData.DUPLICATE_EMAIL,
                RegisterTestData.PASSWORD
        );
        registerPage.submitRegistration();

        AssertionUtils.assertDuplicateEmailError(page);
        DriverFactory.closeDriver();
    }
}
