package base;

import com.microsoft.playwright.Page;
import constants.AppConstants;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }
    public void navigateTo(String endpoint) {
        page.navigate(AppConstants.BASE_URL + endpoint);
        page.waitForURL("**" + endpoint);
    }
    public String getCurrentUrl() {
        return page.url();
    }
}
