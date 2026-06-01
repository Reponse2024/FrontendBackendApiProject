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

public class CategoryTest{
    @Test
    public void testAllCategories() {
        Page page = HandleLoginFailure.setupAndNavigate();
        FilterPage filterPage = new FilterPage(page);

        for (String category : FilterConstants.CATEGORIES) {
            filterPage.selectCategory(category);
            WaitUtils.shortPause(page);
            AssertionUtils.assertProductsOrMessage(filterPage, "category: " + category);
            AssertionUtils.assertUrlContains(filterPage.getCurrentUrl(), AppConstants.PRODUCTS_ENDPOINT);
        }

        DriverFactory.closeDriver();
    }
}
