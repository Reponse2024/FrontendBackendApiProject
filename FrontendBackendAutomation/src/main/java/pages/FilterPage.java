package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FilterPage {
    private final Page page;

    private final String shopNowButton = "a.btn-primary[href='/products']";
    private final String productCard = "a.group.card-hover.block";
    private final String noProductsContainer = "div.text-center.py-24";

    public FilterPage(Page page) {
        this.page = page;
    }

    public void goToFilterPage() {
        page.click(shopNowButton);
        page.waitForURL("**/products");
    }

    public void selectCategory(String category) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                        .setName(category)
                        .setExact(true))
                .click();
        page.waitForSelector(productCard + ", " + noProductsContainer);
    }

    public void setPriceRange(int min, int max) {
        page.fill("input[placeholder='Min']", String.valueOf(min));
        page.fill("input[placeholder='Max']", String.valueOf(max));

        page.keyboard().press("Enter");
        page.waitForSelector("a.group.card-hover.block, div.text-center.py-24");
    }
    public void selectSize(String sizeLabel) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(sizeLabel).setExact(true)).click();
        page.waitForSelector("a.group.card-hover.block, div.text-center.py-24");
    }
    public void selectColor(String colorTitle) {
        page.locator("button[title='" + colorTitle + "']").click();
        page.waitForSelector("a.group.card-hover.block, div.text-center.py-24");
    }
    public void selectSpecial(String specialLabel) {
        page.getByText(specialLabel, new Page.GetByTextOptions().setExact(true)).click();
        page.waitForSelector("a.group.card-hover.block, div.text-center.py-24");
    }
    public void clearAllFilters() {
        page.locator("aside").getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Clear all filters").setExact(true)).click();
        page.waitForSelector("a.group.card-hover.block, div.text-center.py-24");
    }



    public boolean isProductListVisible() {
        return page.locator(productCard).count() > 0;
    }

    public boolean isNoProductsMessageVisible() {
        return page.locator(noProductsContainer).isVisible();
    }

    public int getProductCount() {
        return page.locator(productCard).count();
    }

    public String getCurrentUrl() {
        return page.url();
    }


}
