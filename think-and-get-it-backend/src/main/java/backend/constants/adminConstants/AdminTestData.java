package backend.constants.adminConstants;

import com.github.javafaker.Faker;

public class AdminTestData {
    public static final Faker faker= new Faker();
    public static final String SEARCH_TERM = "Tom";
    public static final String COUPON_CODE = faker.commerce().promotionCode();
    public static final String COUPON_DESCRIPTION = faker.lorem().sentence();
    public static final String DISCOUNT_TYPE = "PERCENTAGE";
    public static final double DISCOUNT_VALUE = faker.number().numberBetween(5, 50);
    public static final double MIN_ORDER_AMOUNT = faker.number().numberBetween(20, 200);
    public static final int MAX_USES = faker.number().numberBetween(10, 500);
    public static final String EXPIRES_AT = "2026-06-30T23:59:59Z";

    public static final double INVALID_DISCOUNT_VALUE = faker.number().randomDouble(2, -100, -10);
    public static final String INVALID_COUPON_CODE = faker.regexify("[!@#$%^&*]{5}");
}
