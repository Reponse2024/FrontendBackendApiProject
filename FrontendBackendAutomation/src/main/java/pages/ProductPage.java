package pages;

import com.microsoft.playwright.Page;
import constants.AddToCartConstants;

public class ProductPage {
    private final Page page;

    public ProductPage(Page page) {
        this.page = page;
    }
    public void quickAddProduct(String productName) {
        page.locator(AddToCartConstants.PRODUCT_CARD + ":has-text('" + productName + "')")
                .locator(AddToCartConstants.QUICK_ADD_BUTTON)
                .click();
    }
    public void addProductFromDetailPage(String productName, String colorName) {
        page.locator(AddToCartConstants.PRODUCT_CARD + ":has-text('" + productName + "')").click();
        page.waitForSelector(AddToCartConstants.ADD_TO_CART_BUTTON);

        page.locator(AddToCartConstants.COLOR_OPTION + "[title='" + colorName + "']").click();

        page.click(AddToCartConstants.ADD_TO_CART_BUTTON);
    }
    public int getCartCount() {
        String badgeText = page.locator(AddToCartConstants.CART_COUNT_BADGE).innerText();
        return Integer.parseInt(badgeText.trim());
    }
    public boolean isCartIconVisible() {
        return page.locator(AddToCartConstants.CART_ICON).isVisible();
    }
}
