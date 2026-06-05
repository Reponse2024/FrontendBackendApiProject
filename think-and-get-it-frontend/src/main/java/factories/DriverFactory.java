package factories;

import com.microsoft.playwright.*;
import constants.AppConstants;

public class DriverFactory {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page initDriver() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

        page.navigate(AppConstants.BASE_URL);

        return page;
    }
    public static void closeDriver() {
        context.close();
        browser.close();
        playwright.close();
    }
}
