package base;

import com.microsoft.playwright.Page;
import constants.AppConstants;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public String getCurrentUrl() {
        return page.url();
    }

    public void goToBaseUrl() {
        page.navigate(AppConstants.BASE_URL);
        page.waitForSelector(AppConstants.START_SHOPPING_LINK);
    }

    public void clickStartShopping() {
        page.click(AppConstants.START_SHOPPING_LINK);
        page.waitForSelector(AppConstants.SHOP_NOW_BUTTON);
    }

}
