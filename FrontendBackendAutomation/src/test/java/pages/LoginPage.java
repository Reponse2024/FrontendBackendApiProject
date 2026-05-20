package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;
    private final String signInLink = "a.btn-ghost.text-sm.py-2.px-4";
    private final String emailInput = "input[type='email'][placeholder='you@example.com']";
    private final String passwordInput = "input[type='password'][placeholder='••••••••']";
    private final String loginButton = "button.btn-primary";

    public LoginPage(Page page) {
        this.page = page;
    }
    public void navigateToLoginPage() {
        page.click(signInLink);
        page.waitForSelector(emailInput);
    }
    public void login(String email, String password) {
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.waitForNavigation(() -> page.click(loginButton));
    }
    public void loginWithEmptyFields() {
        page.fill(emailInput, "");
        page.fill(passwordInput, "");
        page.click(loginButton);
    }
}
