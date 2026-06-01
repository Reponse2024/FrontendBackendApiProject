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

public class SpecialTest {
    @Test
    public void testAllSpecialFilters() {
        Page page = HandleLoginFailure.setupAndNavigate();
        FilterPage filterPage = new FilterPage(page);

        for (String special : FilterConstants.SPECIALS) {
            filterPage.selectSpecial(special);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "special filter: " + special);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }

}
