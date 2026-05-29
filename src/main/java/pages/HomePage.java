package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import constants.AppConstants;
import constants.NavigationConstants;

public class HomePage extends BasePage {
    private final String shopNowButton = "a.btn-primary[href='" + AppConstants.PRODUCTS_ENDPOINT + "']";

    public HomePage(Page page) {
        super(page);
    }
    public void goToProducts() {
        page.click(shopNowButton);
        page.waitForURL("**" + AppConstants.PRODUCTS_ENDPOINT);
    }

    public void goToHome() {
        page.click(NavigationConstants.HOME_LINK);
        page.waitForURL("**/home");
    }

    public void goToShop() {
        page.click(NavigationConstants.SHOP_LINK);
        page.waitForURL("**/products");
    }

    public void goToFlashSale() {
        page.click(NavigationConstants.FLASH_LINK);
        page.waitForURL("**/products?flash_sale=true");
    }

    public void goToFeatured() {
        page.click(NavigationConstants.FEATURED_LINK);
        page.waitForURL("**/products?featured=true");
    }
}
