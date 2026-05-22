package pages;

import com.microsoft.playwright.Page;
import constants.AppConstants;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }
    public void navigateToLoginPage() {
        page.click(AppConstants.SIGN_IN_LINK);
        page.waitForSelector(AppConstants.LOGIN_EMAIL_INPUT);
    }
    public void login(String email, String password) {
        page.fill(AppConstants.LOGIN_EMAIL_INPUT, email);
        page.fill(AppConstants.LOGIN_PASSWORD_INPUT, password);
        page.waitForNavigation(() -> page.click(AppConstants.LOGIN_BUTTON));
    }
    public void loginWithEmptyFields() {
        page.fill(AppConstants.LOGIN_EMAIL_INPUT, "");
        page.fill(AppConstants.LOGIN_PASSWORD_INPUT, "");
        page.click(AppConstants.LOGIN_BUTTON);
    }
}
