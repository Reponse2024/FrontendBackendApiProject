package admin;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.adminConstants.AdminTestData;
import backend.implementFlow.AdminFlow;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utils.ResponseAssertions;
import static spec.SpecBuilder.getRequestSpec;

public class CreateCouponTest {
    @Test
    public void createCouponSuccessfully() {
        RequestSpecification spec = getRequestSpec();
        Response response = new AdminFlow().createCoupon(
                spec,
                AdminTestData.COUPON_CODE,
                AdminTestData.COUPON_DESCRIPTION,
                AdminTestData.DISCOUNT_TYPE,
                AdminTestData.DISCOUNT_VALUE,
                AdminTestData.MIN_ORDER_AMOUNT,
                AdminTestData.MAX_USES,
                AdminTestData.EXPIRES_AT
        );
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ResponseMessages.ADMIN_COUPON_CREATED);
        System.out.print("Response body" +  response.asString() );
    }

    @Test
    public void createCouponFailsWithInvalidData() {
        RequestSpecification spec = getRequestSpec();
        Response response = new AdminFlow().createCoupon(
                spec,
                AdminTestData.INVALID_COUPON_CODE,
                AdminTestData.INVALID_COUPON_DESCRIPTION,
                AdminTestData.DISCOUNT_TYPE,
                AdminTestData.INVALID_DISCOUNT_VALUE,
                AdminTestData.MIN_ORDER_AMOUNT,
                AdminTestData.MAX_USES,
                AdminTestData.EXPIRES_AT
        );
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.ADMIN_INVALID_COUPON);
    }
}
