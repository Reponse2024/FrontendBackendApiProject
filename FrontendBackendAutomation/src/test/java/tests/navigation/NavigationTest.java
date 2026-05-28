package tests.navigation;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import pages.NavigationPage;
import utils.AssertionUtils;
import utils.HandleLoginFailure;

public class NavigationTest {

    @Test
    public void testHomeNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        NavigationPage navPage = new NavigationPage(page);
        navPage.goToHome();
        AssertionUtils.assertHomePage(page);
    }

    @Test
    public void testShopNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        NavigationPage navPage = new NavigationPage(page);
        navPage.goToShop();
        AssertionUtils.assertShopPage(page);
    }

    @Test
    public void testFlashSaleNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        NavigationPage navPage = new NavigationPage(page);
        navPage.goToFlashSale();
        AssertionUtils.assertFlashPage(page);
    }

    @Test
    public void testFeaturedNavigation() {
        Page page = HandleLoginFailure.setupAndNavigate();
        NavigationPage navPage = new NavigationPage(page);
        navPage.goToFeatured();
        AssertionUtils.assertFeaturedPage(page);
    }
}
