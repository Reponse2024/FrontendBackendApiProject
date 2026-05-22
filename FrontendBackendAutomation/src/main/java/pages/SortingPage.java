package pages;

import com.microsoft.playwright.Page;
import constants.AppConstants;
import constants.SortingConstants;

public class SortingPage {
    private final Page page;

    public SortingPage(Page page) {
        this.page = page;
    }
    public void selectSortOption(String optionValue) {
        page.selectOption(SortingConstants.SORT_DROPDOWN, optionValue);
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public String getCurrentSortSelection() {
        return page.locator(SortingConstants.SORT_DROPDOWN).inputValue();
    }
    public boolean isProductListVisible() {
        return page.locator(AppConstants.PRODUCT_CARD).count() > 0;
    }
    public boolean isNoProductsMessageVisible() {
        return page.locator(AppConstants.NO_PRODUCTS_CONTAINER).isVisible();
    }
}
