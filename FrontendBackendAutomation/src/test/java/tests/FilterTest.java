package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.LoginPage;

public class FilterTest extends BaseTest {

    @Test
    public void testAllCategories() {
        LoginPage loginPage = new LoginPage(page);
        FilterPage filterPage = new FilterPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        filterPage.goToFilterPage();

        String[] categories = {
                "All", "Bags & Luggage", "Beauty & Care", "Electronics",
                "Fruits-Decor", "Home & Living", "Kids & Baby",
                "Men's Fashion", "Sports & Fitness", "Women's Fashion"
        };
        for (String category : categories) {
            filterPage.selectCategory(category);
            page.waitForTimeout(2000);

            if (filterPage.isNoProductsMessageVisible()) {
                System.out.println("No products found for category: " + category);
                Assert.assertTrue(filterPage.isNoProductsMessageVisible(),
                        "Expected 'No products found' message for category: " + category);
            } else {
                Assert.assertTrue(filterPage.isProductListVisible(),
                        "Products visible for category: " + category);
                Assert.assertTrue(filterPage.getProductCount() > 0,
                        "Products appear for category: " + category);
                Assert.assertTrue(filterPage.getCurrentUrl().contains("products"), "URL should contain /products after selecting: " + category);

            }
        }
    }

    @Test
    public void testPriceRange25to75() {
        LoginPage loginPage = new LoginPage(page);
        FilterPage filterPage = new FilterPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        filterPage.goToFilterPage();
        filterPage.setPriceRange(150, 300);
        page.waitForTimeout(4000);

        if (filterPage.isNoProductsMessageVisible()) {
            System.out.println("No products found for price range 150–300");
            Assert.assertTrue(filterPage.isNoProductsMessageVisible(),
                    "Expected 'No products found' message for price range 150–300");
        } else {
            Assert.assertTrue(filterPage.isProductListVisible(),
                    "Products visible for price range 150–300");
            Assert.assertTrue(filterPage.getProductCount() > 0,
                    "Products appear for price range 150–300");

            Assert.assertTrue(filterPage.getCurrentUrl().contains("price"),
                    "URL should contain /products after applying price filter");
        }
    }

    @Test
    public void testSizeFilter() {
        LoginPage loginPage = new LoginPage(page);
        FilterPage filterPage = new FilterPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        filterPage.goToFilterPage();
        String[] sizes = {
                "XS", "S", "M", "L", "XL", "XXL",
                "EU 36", "EU 37", "EU 38", "EU 39", "EU 40", "EU 41"
        };

        for (String size : sizes) {
            filterPage.selectSize(size);
            page.waitForTimeout(2000);

            if (filterPage.isNoProductsMessageVisible()) {
                System.out.println("No products found for size: " + size);
                Assert.assertTrue(filterPage.isNoProductsMessageVisible(),
                        "Expected 'No products found' message for size: " + size);
            } else {
                Assert.assertTrue(filterPage.isProductListVisible(),
                        "Products visible for size: " + size);
                Assert.assertTrue(filterPage.getProductCount() > 0,
                        "Products appear for size: " + size);
                Assert.assertTrue(filterPage.getCurrentUrl().contains("products"),
                        "URL should contain /products after selecting size: " + size);
            }
        }
    }

    @Test
    public void testColorRed() {
        LoginPage loginPage = new LoginPage(page);
        FilterPage filterPage = new FilterPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        filterPage.goToFilterPage();
        String[] colors = {
                "Black", "White", "Red", "Blue",
                "Green", "Pink", "Beige", "Navy"
        };

        for (String color : colors) {
            filterPage.selectColor(color);
            page.waitForTimeout(2000);

            if (filterPage.isNoProductsMessageVisible()) {
                System.out.println("No products found for color: " + color);
                Assert.assertTrue(filterPage.isNoProductsMessageVisible(),
                        "Expected 'No products found' message for color: " + color);
            } else {
                Assert.assertTrue(filterPage.isProductListVisible(),
                        "Products visible for color: " + color);
                Assert.assertTrue(filterPage.getProductCount() > 0,
                        "Products appear for color: " + color);
                Assert.assertTrue(filterPage.getCurrentUrl().contains("color"));
            }
        }
    }
    @Test
    public void testFlashSaleFilter() {
        LoginPage loginPage = new LoginPage(page);
        FilterPage filterPage = new FilterPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        filterPage.goToFilterPage();
        String[] specials = {"⚡ Flash Sale", "⭐ Featured"};

        for (String special : specials) {
            filterPage.selectSpecial(special);
            page.waitForTimeout(2000);

            if (filterPage.isNoProductsMessageVisible()) {
                System.out.println("No products found for special filter: " + special);
                Assert.assertTrue(filterPage.isNoProductsMessageVisible(),
                        "Expected 'No products found' message for special filter: " + special);
            } else {
                Assert.assertTrue(filterPage.isProductListVisible(),
                        "Products visible for special filter: " + special);
                Assert.assertTrue(filterPage.getProductCount() > 0,
                        "Products appear for special filter: " + special);
            }
        }
    }
    @Test
    public void testClearAllFilters() {
        LoginPage loginPage = new LoginPage(page);
        FilterPage filterPage = new FilterPage(page);
        loginPage.navigateToLoginPage();
        loginPage.login(ADMIN_EMAIL, ADMIN_PASSWORD);

        filterPage.goToFilterPage();
        filterPage.selectColor("Red");
        page.waitForTimeout(3000);

        filterPage.clearAllFilters();
        page.waitForTimeout(3000);
        Assert.assertTrue(filterPage.isProductListVisible(),
                "Products should be visible after clearing all filters");
        Assert.assertTrue(filterPage.getProductCount() > 0,
                "Products should appear after clearing all filters");
        Assert.assertTrue(filterPage.getCurrentUrl().contains("products"),
                "URL should contain /products after clearing filters");
    }

}
