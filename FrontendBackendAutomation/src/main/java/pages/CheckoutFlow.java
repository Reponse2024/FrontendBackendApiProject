package pages;

import com.microsoft.playwright.Page;
import constants.CheckoutFlowConstants;

public class CheckoutFlow {
    private final Page page;
    public CheckoutFlow(Page page) {
        this.page = page;
    }
    public void goToCheckoutDirect(){
        page.locator(CheckoutFlowConstants.CHECKOUT_BUTTON).click();
    }
    public void addNewAddress(String firstName, String lastName, String phone, String street, String city, String state, String country, String postalCode){
        page.locator(CheckoutFlowConstants.ADD_NEW_ADDRESS_BUTTON).click();
        page.locator("input[placeholder='First name']").fill(firstName);
        page.locator("input[placeholder='Last name']").fill(lastName);
        page.locator("input[placeholder='Phone number']").fill(phone);
        page.locator("input[placeholder='Street address']").fill(street);
        page.locator("input[placeholder='City']").fill(city);
        page.locator("input[placeholder='State / Region']").fill(state);
        page.locator("input[placeholder='Country']").fill(country);
        page.locator("input[placeholder='Postal code (optional)']").fill(postalCode);

        page.locator(CheckoutFlowConstants.SAVE_ADDRESS_BUTTON).click();
        page.locator(CheckoutFlowConstants.CONTINUE_TO_PAYMENT_BUTTON).click();
    }
    public void selectPaymentMethod (String method){
        page.locator(String.format(CheckoutFlowConstants.PAYMENT_METHOD_LABEL, method)).click();
        page.locator(CheckoutFlowConstants.REVIEW_ORDER_BUTTON).click();
    }
    public void placeOrder(){
        page.locator(CheckoutFlowConstants.PLACE_ORDER_BUTTON).click();
        page.locator(CheckoutFlowConstants.ORDER_SUCCESS_MESSAGE).click();
    }
}
