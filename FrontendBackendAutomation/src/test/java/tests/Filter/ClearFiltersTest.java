package tests.Filter;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import factories.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.HomePage;
import pages.LoginPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class ClearFiltersTest {
    @Test
    public void testClearAllFilters() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        FilterPage filterPage = new FilterPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);
        homePage.goToProducts();

        filterPage.selectColor(AppConstants.COLORS[2]);
        WaitUtils.shortPause(page);

        filterPage.clearAllFilters();
        WaitUtils.shortPause(page);

        AssertionUtils.assertProductsOrMessage(filterPage, "after clearing filters");
        Assert.assertTrue(filterPage.getCurrentUrl().endsWith(AppConstants.PRODUCTS_ENDPOINT),
                "URL should end with /products after clearing filters");

        DriverFactory.closeDriver();
    }

}
