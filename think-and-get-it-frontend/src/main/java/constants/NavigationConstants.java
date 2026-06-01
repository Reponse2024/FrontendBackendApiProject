package constants;

public class NavigationConstants {
    // Navbar links
    public static final String NAV_BAR = "nav.hidden.md\\:flex.items-center.gap-1";
    public static final String HOME_LINK = NAV_BAR + " >> a[href='/home']";
    public static final String SHOP_LINK = NAV_BAR + " >> a[href='/products']";
    public static final String FLASH_LINK = NAV_BAR + " >> a[href='/products?flash_sale=true']";
    public static final String FEATURED_LINK = NAV_BAR + " >> a[href='/products?featured=true']";
}
