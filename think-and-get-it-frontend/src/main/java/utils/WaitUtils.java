package utils;

import com.microsoft.playwright.Page;

public class WaitUtils {
    public static void shortPause(Page page) {
        page.waitForTimeout(2000);
    }
    public static void mediumPause(Page page) {
        page.waitForTimeout(4000);
    }
    public static void longPause(Page page) {
        page.waitForTimeout(6000);
    }
}
