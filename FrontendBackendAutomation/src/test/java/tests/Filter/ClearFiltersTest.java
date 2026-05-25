package tests.Filter;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import constants.FilterConstants;
import factories.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FilterPage;
import utils.AssertionUtils;
import utils.HandleLoginFailure;
import utils.WaitUtils;

public class ClearFiltersTest {
    @Test
    public void testClearAllFilters() {
        Page page = HandleLoginFailure.setupAndNavigate();
        FilterPage filterPage = new FilterPage(page);

        filterPage.selectColor(FilterConstants.COLORS[2]);
        WaitUtils.shortPause(page);

        filterPage.clearAllFilters();
        WaitUtils.shortPause(page);

        AssertionUtils.assertProductsOrMessage(filterPage, "after clearing filters");
        Assert.assertTrue(filterPage.getCurrentUrl().endsWith(AppConstants.PRODUCTS_ENDPOINT),
                "URL should end with /products after clearing filters");

        DriverFactory.closeDriver();
    }

}
