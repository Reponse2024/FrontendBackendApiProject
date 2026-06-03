package backend.constants;

public class ApiEndpoints {
    // Auth
    public static final String REGISTER = "/auth/register";
    public static final String LOGIN = "/auth/login";
    public static final String REFRESH = "/auth/refresh";
    public static final String VERIFY_EMAIL = "/auth/verify-email";
    public static final String FORGOT_PASSWORD = "/auth/forgot-password";
    public static final String RESET_PASSWORD = "/auth/reset-password";
    public static final String ME = "/auth/me";

    // Users
    public static final String PROFILE = "/users/profile";
    public static final String AVATAR = "/users/avatar";
    public static final String CHANGE_PASSWORD = "/users/change-password";
    public static final String ADDRESSES = "/users/addresses";

    // Categories
    public static final String CATEGORIES = "/categories";
    public static final String CATEGORY_BY_SLUG = "/categories/{slug}";

}
