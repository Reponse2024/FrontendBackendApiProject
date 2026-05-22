package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import constants.AppConstants;

public class HomePage extends BasePage {
    private final String shopNowButton = "a.btn-primary[href='" + AppConstants.PRODUCTS_ENDPOINT + "']";

    public HomePage(Page page) {
        super(page);
    }
    public void goToProducts() {
        page.click(shopNowButton);
        page.waitForURL("**" + AppConstants.PRODUCTS_ENDPOINT);
    }
}
