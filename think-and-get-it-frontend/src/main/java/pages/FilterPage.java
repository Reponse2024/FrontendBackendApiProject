package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import constants.AppConstants;

public class FilterPage extends BasePage {

    public FilterPage(Page page) {
        super(page);
    }
    public void selectCategory(String category) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(category).setExact(true)).click();
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public void setPriceRange(int min, int max) {
        page.fill("input[placeholder='Min']", String.valueOf(min));
        page.fill("input[placeholder='Max']", String.valueOf(max));
        page.keyboard().press("Enter");
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public void selectPricePreset(String rangeLabel) {
        page.getByText(rangeLabel, new Page.GetByTextOptions().setExact(true)).click();
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public void selectSize(String sizeLabel) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(sizeLabel).setExact(true)).click();
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public void selectColor(String colorTitle) {
        page.locator("button[title='" + colorTitle + "']").click();
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public void selectSpecial(String specialLabel) {
        page.getByText(specialLabel, new Page.GetByTextOptions().setExact(true)).click();
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public void clearAllFilters() {
        page.locator("aside").getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("Clear all filters").setExact(true)).click();
        page.waitForSelector(AppConstants.PRODUCT_CARD + ", " + AppConstants.NO_PRODUCTS_CONTAINER);
    }
    public boolean isProductListVisible() {
        return page.locator(AppConstants.PRODUCT_CARD).count() > 0;
    }
    public boolean isNoProductsMessageVisible() {
        return page.locator(AppConstants.NO_PRODUCTS_CONTAINER).isVisible();
    }
    public int getProductCount() {
        return page.locator(AppConstants.PRODUCT_CARD).count();
    }
}
