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
            //Expected Messages
    public static final String ITEM_ID_SHOULD_NOT_BE_NULL = "Item ID should not be null";
    public static final String NO_COUPON_AVAILABLE = "No coupon code available in cart to apply.";

    // Orders messages
    public static final String ORDER_PLACED = "Order placed successfully";
    public static final String ORDER_CANCELLED = "Order cancelled";
    public static final String ORDER_RETURN_REQUESTED = "Return request submitted";
    public static final String PAYMENT_PROOF_UPLOADED = "Payment proof uploaded. We will verify and confirm your order.";
    public static final String ORDER_STATUS_UPDATED = "Order status updated";
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_RETURN_WARNING = "Only delivered orders can be returned";
    public static final String ORDER_ALREADY_CANCELLED = "Only pending or confirmed orders can be cancelled";
    public static final String NO_FILE_UPLOADED = "No file uploaded";
    public static final String CART_EMPTY = "Your cart is empty";
    public static final String STATUS_SHOULD_BE_DELIVERED = "Order status should be DELIVERED";
    public static final String EXPECTED_200 = "Expected 200 OK";
    public static final String ORDER_ID_CAN_NOT_BE_NULL = "Order ID can not be null";

    //Reviews
    public static final String RECORD_NOT_FOUND = "Related record not found";
    public static final String REVIEW_SUBMITTED = "Review submitted";

    //Wishlist
    public static final String WISHLIST_FETCHED = "Success";
    public static final String WISHLIST_ADDED = "Added to wishlist";
    public static final String WISHLIST_ALREADY_EXISTS = "Already in wishlist";
    public static final String WISHLIST_REMOVED = "Removed from wishlist";
    public static final String WISHLIST_MOVED_TO_CART = "Wishlist item moved to cart successfully";
    public static final String AT_LEAST_ONE_ITEM = "Wishlist should contain at least one product";

    //Search
    public static final String SEARCH_TRENDING = "Trending searches fetched successfully";
    public static final String SEARCH_QUERY_REQUIRED = "Search query is required";
    public static final String SEARCH_NO_RESULTS = "No products found for the given query";
    public static final String UNAUTHORIZED = "Unauthorized access";
}
