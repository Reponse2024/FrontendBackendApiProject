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

public class PriceTest {
    @Test
    public void testPriceFilters() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        FilterPage filterPage = new FilterPage(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);
        homePage.goToProducts();

        // Numeric ranges
        for (int[] range : AppConstants.PRICE_RANGES) {
            filterPage.setPriceRange(range[0], range[1]);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "price range " + range[0] + "–" + range[1]);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }
        // Preset labels
        for (String preset : AppConstants.PRICE_PRESETS) {
            filterPage.selectPricePreset(preset);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "preset " + preset);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }
}
