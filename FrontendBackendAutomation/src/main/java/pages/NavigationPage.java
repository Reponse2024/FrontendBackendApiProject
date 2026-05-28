package pages;

import com.microsoft.playwright.Page;
import constants.NavigationConstants;

public class NavigationPage {
    private final Page page;

    public NavigationPage(Page page) {
        this.page = page;
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
