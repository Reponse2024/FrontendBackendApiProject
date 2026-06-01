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

public class ColorTest {
    @Test
    public void testAllColors() {
        Page page = HandleLoginFailure.setupAndNavigate();
        FilterPage filterPage = new FilterPage(page);

        for (String color : FilterConstants.COLORS) {
            filterPage.selectColor(color);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "color: " + color);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }

}
