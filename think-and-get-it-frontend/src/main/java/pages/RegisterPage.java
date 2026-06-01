package pages;

import com.microsoft.playwright.Page;
import constants.RegisterConstants;

public class RegisterPage {
    private final Page page;

    public RegisterPage(Page page) {
        this.page = page;
    }
    public void fillRegisterForm(String firstName, String lastName, String email, String password) {
        page.locator(RegisterConstants.FIRST_NAME_INPUT).fill(firstName);
        page.locator(RegisterConstants.LAST_NAME_INPUT).fill(lastName);
        page.locator(RegisterConstants.EMAIL_INPUT).fill(email);
        page.locator(RegisterConstants.PASSWORD_INPUT).fill(password);
    }
    public void submitRegistration() {
        page.locator(RegisterConstants.CREATE_ACCOUNT_SUBMIT).click();
    }
}
