package utils;

import base.BasePage;
import com.microsoft.playwright.Page;
import constants.AppConstants;
import factories.DriverFactory;
import pages.HomePage;
import pages.LoginPage;

public class HandleLoginFailure {

    public static Page setupAndNavigate() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        BasePage basePage = new BasePage(page);

        try {
            loginPage.navigateToLoginPage();
            loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);

            if (page.url().contains(AppConstants.HOME_ENDPOINT)) {
                homePage.goToProducts();
                return page;
            } else {
                throw new RuntimeException("Login did not redirect to home");
            }
        } catch (Exception e) {
            basePage.goToBaseUrl();
            if (page.url().contains(AppConstants.LOGIN_ENDPOINT)) {
                page.waitForTimeout(2000);
                page.navigate(AppConstants.BASE_URL);
                page.waitForSelector(AppConstants.START_SHOPPING_LINK,
                        new Page.WaitForSelectorOptions().setTimeout(7000));

            }
            basePage.clickStartShopping();
            homePage.goToProducts();
            return page;
        }
    }
}
