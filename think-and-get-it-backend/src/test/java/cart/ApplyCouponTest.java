package cart;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.cartConstants.CartTestData;
import backend.implementFlow.CartFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class ApplyCouponTest {

        @Test
        public void applyCouponFromCart() {

            CartFlow cartFlow = new CartFlow();
            String couponCode = cartFlow.getCouponCode(getRequestSpec());

            if (couponCode != null && !couponCode.isEmpty()) {
                Response response = cartFlow.applyCoupon(getRequestSpec(), couponCode);
                ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.COUPON_APPLIED);
            } else {
                System.out.println(ResponseMessages.NO_COUPON_AVAILABLE);
            }
        }

    @Test
    public void applyInvalidCoupon() {
        Response response = new CartFlow().applyCoupon(getRequestSpec(), CartTestData.INVALID_COUPON);
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.COUPON_INVALID);
    }
    }
