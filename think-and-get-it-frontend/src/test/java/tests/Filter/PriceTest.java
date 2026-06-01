package tests.Filter;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import constants.FilterConstants;
import factories.DriverFactory;
import org.testng.annotations.Test;
import pages.FilterPage;
import utils.AssertionUtils;
import utils.HandleLoginFailure;
import utils.WaitUtils;

public class PriceTest {
    @Test
    public void testPriceFilters() {
        Page page = HandleLoginFailure.setupAndNavigate();
        FilterPage filterPage = new FilterPage(page);

        // Numeric ranges
        for (int[] range : FilterConstants.PRICE_RANGES) {
            filterPage.setPriceRange(range[0], range[1]);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "price range " + range[0] + "–" + range[1]);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }
        // Preset labels
        for (String preset : FilterConstants.PRICE_PRESETS) {
            filterPage.selectPricePreset(preset);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "preset " + preset);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }
}
