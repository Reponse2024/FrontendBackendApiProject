package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    public static final String BASE_URL = "https://think-and-get-it-frontend.onrender.com";
    public static final String ADMIN_EMAIL = "admin@thinkandgetit.com";
    public static final String ADMIN_PASSWORD = "Admin@123456";

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(BASE_URL);
    }
    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
