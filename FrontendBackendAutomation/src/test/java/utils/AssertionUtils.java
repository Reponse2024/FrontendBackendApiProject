package utils;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import constants.CheckoutFlowConstants;
import constants.RegisterConstants;
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
    public static void assertProductInCart(ProductPage productPage, String expectedProductName) {
        Assert.assertTrue(productPage.isProductInCart(expectedProductName),
                "Cart should contain the product: " + expectedProductName);
    }

    //Checkout Flow Specific Assertions
    public static void assertOrderPlaced(Page page){
        Assert.assertTrue(page.locator(CheckoutFlowConstants.ORDER_SUCCESS_MESSAGE).isVisible(), "Order success message should be visible");
    }

    //Register Specific Assertions
    public static void assertRegistrationSuccess(Page page) {
            WaitUtils.mediumPause(page);
            Assert.assertTrue(page.url().contains(AppConstants.HOME_ENDPOINT),
                    "User should be redirected to homepage after registration");
    }

    public static void assertPasswordError(Page page) {
            Assert.assertTrue(page.locator(RegisterConstants.PASSWORD_ERROR).isVisible(),
                    "Weak password error should be displayed");
    }

    public static void assertDuplicateEmailError(Page page) {
        page.waitForSelector(RegisterConstants.DUPLICATE_EMAIL_TOAST);
        Assert.assertTrue(page.locator(RegisterConstants.DUPLICATE_EMAIL_TOAST).isVisible(),
                "Duplicate email error toast should be displayed");
    }

    public static void assertInvalidEmail(Page page) {
        boolean isInvalid = (boolean) page.evaluate(
                "document.querySelector('input[type=\"email\"]').checkValidity() === false"
        );
        String validationMessage = (String) page.evaluate(
                "document.querySelector('input[type=\"email\"]').validationMessage"
        );

        Assert.assertTrue(isInvalid, "Email field should be invalid");
        System.out.println("Browser validation message: " + validationMessage);
    }

    public static void assertEmptyField(Page page, String selector) {
        boolean isInvalid = (boolean) page.locator(selector).evaluate("el => !el.checkValidity()");
        String validationMessage = (String) page.locator(selector).evaluate("el => el.validationMessage");

        Assert.assertTrue(isInvalid, "Field should be invalid when empty");
        System.out.println("Browser validation message: " + validationMessage);
    }

}
