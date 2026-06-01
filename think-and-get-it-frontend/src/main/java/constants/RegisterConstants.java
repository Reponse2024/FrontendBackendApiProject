package constants;

public class RegisterConstants {
    // Base page
    public static final String CREATE_ACCOUNT_BUTTON = "a.btn-ghost:has-text('Create account — free')";

    // Register form
    public static final String FIRST_NAME_INPUT = "input[placeholder='John']";
    public static final String LAST_NAME_INPUT = "input[placeholder='Doe']";
    public static final String EMAIL_INPUT = "input[type='email'][placeholder='you@example.com']";
    public static final String PASSWORD_INPUT = "input[type='password'][placeholder='Min. 8 characters']";
    public static final String CREATE_ACCOUNT_SUBMIT = "button.btn-primary:has-text('Create account')";

    // Error messages (adapt to app’s actual DOM)
    public static final String PASSWORD_ERROR = "text=Password must be at least 8 characters";
    public static final String DUPLICATE_EMAIL_TOAST = "div[role='status']:has-text('Email already registered')";
}
