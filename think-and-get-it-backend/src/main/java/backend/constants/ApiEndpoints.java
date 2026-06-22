package backend.constants;

public class ApiEndpoints {
    // Auth
    public static final String REGISTER = "/auth/register";
    public static final String LOGIN = "/auth/login";
    public static final String REFRESH = "/auth/refresh";
    public static final String VERIFY_EMAIL = "/auth/verify-email/{token}";
    public static final String FORGOT_PASSWORD = "/auth/forgot-password";
    public static final String RESET_PASSWORD = "/auth/reset-password/{token}";
    public static final String ME = "/auth/me";

    // Users
    public static final String PROFILE = "/users/profile";
    public static final String AVATAR = "/users/avatar";
    public static final String CHANGE_PASSWORD = "/users/change-password";
    public static final String ADDRESSES = "/users/addresses";

    // Categories
    public static final String CATEGORIES = "/categories";
    public static final String CATEGORY_BY_SLUG = "/categories/{slug}";

    // Products
    public static final String PRODUCTS = "/products";
    public static final String PRODUCT_BY_SLUG = "/products/{slug}";
    public static final String PRODUCT_BY_ID = "/products/{id}";
    public static final String PRODUCT_IMAGES = "/products/{id}/images";
    public static final String PRODUCT_TRENDING = "/products/trending";
    public static final String PRODUCT_FLASH_SALES = "/products/flash-sales";
    public static final String PRODUCT_RELATED = "/products/{id}/related";

    //Cart
    public static final String CART = "/cart";
    public static final String CART_ITEMS = "/cart/items";
    public static final String CART_ITEM_BY_ID = "/cart/items/{itemId}";
    public static final String CART_SAVE_FOR_LATER = "/cart/items/{itemId}/save-for-later";
    public static final String CART_COUPON = "/cart/coupon";

    //Orders
    public static final String ORDERS = "/orders";
    public static final String ORDER_BY_ID = "/orders/{id}";
    public static final String ORDER_CANCEL = "/orders/{id}/cancel";
    public static final String ORDER_RETURN = "/orders/{id}/return";
    public static final String ORDER_PAYMENT_PROOF = "/orders/{id}/payment-proof";
    public static final String ORDER_ADMIN_ALL = "/orders/admin/all";
    public static final String ORDER_ADMIN_STATUS = "/orders/admin/{id}/status";

    //Reviews
    public static final String REVIEWS = "/reviews/{productId}";

    //Wishlist
    public static final String WISHLIST = "/wishlist";
    public static final String WISHLIST_PRODUCT = "/wishlist/{productId}";
    public static final String WISHLIST_MOVE_TO_CART = "/wishlist/{productId}/move-to-cart";

    //Search
    public static final String SEARCH = "/search";
    public static final String SEARCH_SUGGESTIONS = "/search/suggestions";
    public static final String SEARCH_TRENDING = "/search/trending";

    //Banners
    public static final String BANNERS = "/banners";

    //Admin
    public static final String ADMIN_DASHBOARD = "/admin/dashboard";
    public static final String ADMIN_USERS = "/admin/users";
    public static final String ADMIN_COUPONS = "/admin/coupons";
    }

