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
        WaitUtils.shortPause(page);

        AssertionUtils.assertQuickAdd(productPage, AddToCartConstants.ZIP_FANNY_PACK);
        AssertionUtils.assertCartItemCount(productPage, 1);

        DriverFactory.closeDriver();
    }

    @Test
    public void testAddZipFannyPackFromDetailPage() {
        Page page = HandleLoginFailure.setupAndNavigate();
        ProductPage productPage = new ProductPage(page);

        productPage.addProductFromDetailPage(AddToCartConstants.ZIP_FANNY_PACK,AddToCartConstants.COLOR_SPECIFIC);
        WaitUtils.longPause(page);

        AssertionUtils.assertAddToCart(productPage, AddToCartConstants.ZIP_FANNY_PACK);
        AssertionUtils.assertCartItemCount(productPage, 1);

        DriverFactory.closeDriver();
    }

}
