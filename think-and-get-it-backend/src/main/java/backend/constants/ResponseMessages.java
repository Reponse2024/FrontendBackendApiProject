package backend.constants;

public class ResponseMessages {
    //Auth messages
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String REGISTER_SUCCESS = "Registration successful. Check your email to verify.";
    public static final String REFRESH_SUCCESS = "Success";
    public static final String EMAIL_VERIFIED = "Email verified successfully";
    public static final String RESET_EMAIL_SENT = "If an account with that email exists, a reset link has been sent.";
    public static final String PASSWORD_RESET_SUCCESS = "Password reset successful";
    public static final String PASSWORD_RESET_FAIL = "Invalid or expired reset token";
    public static final String ME_SUCCESS = "Success";
    public static final String VERIFY_EMAIL_FAIL = "Invalid or expired verification token";

    // Cart messages
    public static final String CART_CLEARED = "Cart cleared";
    public static final String ITEM_ADDED = "Item added to cart";
    public static final String CART_UPDATED = "Cart updated";
    public static final String ITEM_REMOVED = "Item removed";
    public static final String ITEM_SAVED_FOR_LATER = "Saved for later";
    public static final String COUPON_APPLIED = "Coupon applied successfully";
    public static final String COUPON_INVALID = "Invalid or expired coupon code";
    public static final String ITEM_ID_SHOULD_NOT_BE_NULL = "Item ID should not be null";
    public static final String NO_COUPON_AVAILABLE = "No coupon code available in cart to apply.";
}
