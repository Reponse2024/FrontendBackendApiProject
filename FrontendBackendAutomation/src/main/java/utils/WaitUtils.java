package utils;

import com.microsoft.playwright.Page;

public class WaitUtils {
    public static void shortPause(Page page) {
        page.waitForTimeout(2000);
    }
}
