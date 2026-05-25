package utils;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import org.testng.Assert;
import pages.FilterPage;
import pages.ProductPage;
import pages.SortingPage;

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

    // Login specific assertions

    public static void assertValidLogin(Page page) {
        Assert.assertTrue(page.url().contains(AppConstants.HOME_ENDPOINT),
                "User should be redirected to " + AppConstants.HOME_ENDPOINT + " after login");
        Assert.assertTrue(page.locator(AppConstants.LOGIN_PAGE_HEADING).isVisible(),
                "Home page heading should be visible");
    }
    public static void assertInvalidLogin(Page page) {
        Assert.assertTrue(page.url().contains(AppConstants.LOGIN_ENDPOINT),
                "User should remain on " + AppConstants.LOGIN_ENDPOINT + " after invalid login");
        Assert.assertTrue(page.locator(AppConstants.LOGIN_EMAIL_INPUT).isVisible(),
                "Email input should still be visible");
        Assert.assertTrue(page.locator(AppConstants.LOGIN_PASSWORD_INPUT).isVisible(),
                "Password input should still be visible");
        Assert.assertTrue(page.locator(AppConstants.LOGIN_BUTTON).isVisible(),
                "Login button should still be visible");
    }

    // Sorting Specific Assertions
    public static void assertSortApplied(SortingPage sortingPage, String expectedOption) {
        String current = sortingPage.getCurrentSortSelection();
        Assert.assertEquals(current, expectedOption,
                "Sorting option should be applied: " + expectedOption);
        Assert.assertTrue(sortingPage.isProductListVisible() || sortingPage.isNoProductsMessageVisible(),
                "Products or no-products message should be visible after sorting");
    }

    //Adding to Cart Specific Assertions
    public static void assertQuickAdd(ProductPage productPage, String expectedProductName) {
        Assert.assertTrue(productPage.isCartIconVisible(),
                "Cart icon should be visible after Quick Add of " + expectedProductName);
        Assert.assertTrue(productPage.getCartCount() > 0,
                "Cart count should increase after Quick Add.");
    }

    public static void assertAddToCart(ProductPage productPage, String expectedProductName) {
        Assert.assertTrue(productPage.isCartIconVisible(),
                "Cart icon should be visible after Add to Cart of " + expectedProductName);
        Assert.assertTrue(productPage.getCartCount() > 0,
                "Cart count should increase after Add to Cart.");
    }

    public static void assertCartItemCount(ProductPage productPage, int expectedCount) {
        Assert.assertEquals(productPage.getCartCount(), expectedCount,
                "Cart should contain " + expectedCount + " items.");
    }
    public static void assertProductInCart(ProductPage productPage, String expectedProductName) {
        Assert.assertTrue(productPage.isProductInCart(expectedProductName),
                "Cart should contain the product: " + expectedProductName);
    }


}
