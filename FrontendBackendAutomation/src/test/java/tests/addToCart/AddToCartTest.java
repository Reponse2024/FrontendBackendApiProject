package tests.addToCart;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;
import factories.DriverFactory;
import pages.ProductPage;
import utils.AssertionUtils;
import utils.HandleLoginFailure;
import constants.AddToCartConstants;
import utils.WaitUtils;

public class AddToCartTest {

    @Test
    public void testQuickAddZipFannyPack() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.quickAddProduct(AddToCartConstants.ZIP_FANNY_PACK);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.ZIP_FANNY_PACK);

        DriverFactory.closeDriver();
    }
    @Test
    public void testAddZipFannyPackFromDetailPage() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.addProductFromDetailPage(AddToCartConstants.ZIP_FANNY_PACK, AddToCartConstants.COLOR_BLACK);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.ZIP_FANNY_PACK);

        DriverFactory.closeDriver();
    }


    @Test
    public void testQuickAddMiniCrossbody() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.quickAddProduct(AddToCartConstants.MINI_CROSSBODY);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.MINI_CROSSBODY);

        DriverFactory.closeDriver();
    }

    @Test
    public void testAddMiniCrossbodyFromDetailPage() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.addProductFromDetailPage(AddToCartConstants.MINI_CROSSBODY, AddToCartConstants.COLOR_BROWN);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.MINI_CROSSBODY);

        DriverFactory.closeDriver();
    }
    @Test
    public void testAddAProductTwice() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.addProductFromDetailPage(AddToCartConstants.MINI_CROSSBODY, AddToCartConstants.COLOR_BROWN);
        productPage.addProductFromDetailPage(AddToCartConstants.MINI_CROSSBODY, AddToCartConstants.COLOR_BROWN);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.MINI_CROSSBODY);

        DriverFactory.closeDriver();
    }

    @Test
    public void testAddMultipleProducts() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.quickAddProduct(AddToCartConstants.CANVAS_SHOPPER);
        productPage.addProductFromDetailPage(AddToCartConstants.LEATHER_TOTE, AddToCartConstants.COLOR_BROWN);
        WaitUtils.mediumPause(page);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.CANVAS_SHOPPER);
        AssertionUtils.assertProductInCart(productPage, AddToCartConstants.LEATHER_TOTE);

        DriverFactory.closeDriver();
    }
}
