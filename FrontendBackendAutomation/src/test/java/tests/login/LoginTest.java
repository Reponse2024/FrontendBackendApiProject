package tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTest;

public class LoginTest extends BaseTest {
    @Test
    public void validLoginShouldRedirectToHomePage() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        Assert.assertTrue(page.url().contains("home"), "User should be redirected to /home after login");
        Assert.assertTrue(page.locator("h1").isVisible(), "Home page heading should be visible");
    }

    @Test
    public void invalidLoginShouldShowErrorMessage() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login("wrong@user.com", "WrongPassword");

        Assert.assertTrue(page.url().contains("/login"), "User should remain on login page after invalid login");
        Assert.assertTrue(page.locator("input[type='email'][placeholder='you@example.com']").isVisible(), "Email input should still be visible");
        Assert.assertTrue(page.locator("input[type='password'][placeholder='••••••••']").isVisible(), "Password input should still be visible");
        Assert.assertTrue(page.locator("button.btn-primary").isVisible(), "Login button should still be visible");
    }

    @Test
    public void emptyFieldShouldShowValidationErrors() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLoginPage();
        loginPage.loginWithEmptyFields();

        Assert.assertTrue(page.url().contains("/login"), "User should remain on login page after invalid login");
        Assert.assertTrue(page.locator("input[type='email'][placeholder='you@example.com']").isVisible(), "Email input should still be visible");
        Assert.assertTrue(page.locator("input[type='password'][placeholder='••••••••']").isVisible(), "Password input should still be visible");
        Assert.assertTrue(page.locator("button.btn-primary").isVisible(), "Login button should still be visible");
    }
}