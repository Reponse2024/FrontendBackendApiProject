package tests.navigation;

import base.BasePage;
import com.microsoft.playwright.Page;
import factories.DriverFactory;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.AssertionUtils;
import utils.HandleLoginFailure;

public class NavigationTest {

// Navigation with Authentication
    @Test
    public void testHomeNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        HomePage homePage = new HomePage(page);
        homePage.goToHome();
        AssertionUtils.assertHomePage(page);
    }

    @Test
    public void testShopNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        HomePage homePage = new HomePage(page);
        homePage.goToShop();
        AssertionUtils.assertShopPage(page);
    }

    @Test
    public void testFlashSaleNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        HomePage homePage = new HomePage(page);
        homePage.goToFlashSale();
        AssertionUtils.assertFlashPage(page);
    }

    @Test
    public void testFeaturedNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        HomePage homePage = new HomePage(page);
        homePage.goToFeatured();
        AssertionUtils.assertFeaturedPage(page);
    }

    //Navigation without Authentication
    @Test
    public void testBaseToHomeNavigation() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        basePage.clickStartShopping();
        AssertionUtils.assertHomePage(page);

        DriverFactory.closeDriver();
    }

    @Test
    public void testHomeToShopNavigation() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        HomePage homePage = new HomePage(page);
        basePage.clickStartShopping();
        homePage.goToShop();
        AssertionUtils.assertShopPage(page);

        DriverFactory.closeDriver();
    }

    @Test
    public void testHomeToFlashNavigation() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        HomePage homePage = new HomePage(page);
        basePage.clickStartShopping();
        homePage.goToFlashSale();
        AssertionUtils.assertFlashPage(page);

        DriverFactory.closeDriver();
    }

    @Test
    public void testHomeToFeaturedNavigation() {
        Page page = DriverFactory.initDriver();
        BasePage basePage = new BasePage(page);
        HomePage homePage = new HomePage(page);
        basePage.clickStartShopping();
        homePage.goToFeatured();
        AssertionUtils.assertFeaturedPage(page);

        DriverFactory.closeDriver();
    }
}
