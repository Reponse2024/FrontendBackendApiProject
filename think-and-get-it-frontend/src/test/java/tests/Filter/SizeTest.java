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

public class SizeTest {
    @Test
    public void testAllSizes() {
        Page page = HandleLoginFailure.setupAndNavigate();
        FilterPage filterPage = new FilterPage(page);

        for (String size : FilterConstants.SIZES) {
            filterPage.selectSize(size);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "size: " + size);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }

}
