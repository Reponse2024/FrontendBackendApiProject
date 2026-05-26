package tests.checkout;

import base.BasePage;
import com.microsoft.playwright.Page;
import constants.AddToCartConstants;
import constants.AppConstants;
import constants.CheckoutTestData;
import factories.DriverFactory;
import org.testng.annotations.Test;
import pages.CheckoutFlow;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utils.AssertionUtils;
import utils.WaitUtils;

public class CheckoutFlowTest {
    @Test
    public void testCheckoutFlowWithLogin() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        ProductPage productPage = new ProductPage(page);
        CheckoutFlow checkoutFlow = new CheckoutFlow(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);
        homePage.goToProducts();
        productPage.quickAddProduct(AddToCartConstants.ZIP_FANNY_PACK);
        checkoutFlow.goToCheckoutDirect();

        checkoutFlow.addNewAddress(
                CheckoutTestData.FIRST_NAME,
                CheckoutTestData.LAST_NAME,
                CheckoutTestData.PHONE,
                CheckoutTestData.STREET,
                CheckoutTestData.CITY,
                CheckoutTestData.STATE,
                CheckoutTestData.COUNTRY,
                CheckoutTestData.POSTAL_CODE
        );
        checkoutFlow.continueToPayment();
        checkoutFlow.selectPaymentMethod(CheckoutTestData.PAYMENT_METHOD);
        checkoutFlow.placeOrder();
        page.waitForLoadState();

        AssertionUtils.assertOrderPlaced(page);

        DriverFactory.closeDriver();
    }
    @Test
    public void testCheckoutFlowWithoutLogin() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        HomePage homePage = new HomePage(page);
        ProductPage productPage = new ProductPage(page);
        CheckoutFlow checkoutFlow = new CheckoutFlow(page);

        basePage.clickStartShopping();
        homePage.goToProducts();
        productPage.quickAddProduct(AddToCartConstants.ZIP_FANNY_PACK);
        checkoutFlow.goToCheckoutDirect();
        WaitUtils.mediumPause(page);
        AssertionUtils.assertInvalidLogin(page);

        DriverFactory.closeDriver();
    }
    @Test
    public void testCheckoutFlowWithoutAddress() {
        Page page = DriverFactory.initDriver();
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        ProductPage productPage = new ProductPage(page);
        CheckoutFlow checkoutFlow = new CheckoutFlow(page);

        loginPage.navigateToLoginPage();
        loginPage.login(AppConstants.ADMIN_EMAIL, AppConstants.ADMIN_PASSWORD);
        homePage.goToProducts();
        productPage.quickAddProduct(AddToCartConstants.ZIP_FANNY_PACK);
        checkoutFlow.goToCheckoutDirect();
        checkoutFlow.continueToPayment();
        checkoutFlow.selectPaymentMethod(CheckoutTestData.PAYMENT_METHOD);
        checkoutFlow.placeOrder();
        page.waitForLoadState();

        AssertionUtils.assertOrderPlaced(page);

        DriverFactory.closeDriver();
    }

}
