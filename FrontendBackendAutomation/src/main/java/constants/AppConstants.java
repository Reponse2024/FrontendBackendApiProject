package constants;

public class AppConstants {

    public static final String BASE_URL = "https://think-and-get-it-frontend.onrender.com";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String PRODUCTS_ENDPOINT = "/products";

    public static final String ADMIN_EMAIL = "admin@thinkandgetit.com";
    public static final String ADMIN_PASSWORD = "Admin@123456";

    //Data and Payloads
    public static final String[] CATEGORIES = {
            "All", "Bags & Luggage", "Beauty & Care", "Electronics",
            "Fruits-Decor", "Home & Living", "Kids & Baby",
            "Men's Fashion", "Sports & Fitness", "Women's Fashion"
    };
    public static final String[] SIZES = {
            "XS", "S", "M", "L", "XL", "XXL",
            "EU 36", "EU 37", "EU 38", "EU 39", "EU 40", "EU 41"
    };
    public static final String[] COLORS = {
            "Black", "White", "Red", "Blue",
            "Green", "Pink", "Beige", "Navy"
    };
    public static final String[] SPECIALS = {
            "⚡ Flash Sale", "⭐ Featured"
    };
    public static final int[][] PRICE_RANGES = {
            {0, 25}, {25, 75}, {75, 150}, {150, 9999}
    };
    public static final String[] PRICE_PRESETS = {
            "$0–$25", "$25–$75", "$75–$150", "$150+"
    };
}
