package tests.Filter;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import factories.DriverFactory;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.HomePage;
import pages.LoginPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class CategoryTest{
    @Test
    public void testAllCategories() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        FilterPage filterPage = new FilterPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);
        homePage.goToProducts();

        for (String category : AppConstants.CATEGORIES) {
            filterPage.selectCategory(category);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "category: " + category);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }
}
