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
    public void addProductFromDetailPage(String productName, String color) {
        page.locator(String.format(AddToCartConstants.PRODUCT_CARD_TITLE, productName)).click();
        page.waitForSelector(String.format(AddToCartConstants.COLOR_OPTION_BUTTON, color));
        page.locator(String.format(AddToCartConstants.COLOR_OPTION_BUTTON, color)).click();
        page.locator(AddToCartConstants.ADD_TO_CART_BUTTON).click();
        page.waitForSelector(AddToCartConstants.CART_OVERLAY_CONTAINER);
    }




    public int getCartCount() {
        String badgeText = page.locator(AddToCartConstants.CART_COUNT_BADGE).innerText();
        return Integer.parseInt(badgeText.trim());
    }
    public boolean isCartIconVisible() {
        return page.locator(AddToCartConstants.CART_ICON).isVisible();
    }
    public boolean isProductInCart(String productName) {
        return page.locator(AddToCartConstants.CART_ITEM_NAME + ":has-text('" + productName + "')").isVisible();
    }




}
