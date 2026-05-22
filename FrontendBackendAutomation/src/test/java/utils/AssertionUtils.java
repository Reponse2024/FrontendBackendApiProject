package utils;

import org.testng.Assert;
import pages.FilterPage;

public class AssertionUtils {
    public static void assertProductsOrMessage(FilterPage filterPage, String context) {
        if (filterPage.isNoProductsMessageVisible()) {
            Assert.assertTrue(filterPage.isNoProductsMessageVisible(),
                    "Expected 'No products found' for " + context);
        } else {
            Assert.assertTrue(filterPage.isProductListVisible(),
                    "Products visible for " + context);
            Assert.assertTrue(filterPage.getProductCount() > 0,
                    "Products appear for " + context);
        }
    }

    public static void assertUrlContains(String url, String endpoint) {
        Assert.assertTrue(url.contains(endpoint),
                "URL should contain " + endpoint);
    }
}
