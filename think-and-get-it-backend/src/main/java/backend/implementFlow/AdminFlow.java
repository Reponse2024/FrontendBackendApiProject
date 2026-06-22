package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.tokenManager.TokenManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AdminFlow {

    public Response getDashboard(RequestSpecification spec) {
        String token = TokenManager.getAdminAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.ADMIN_DASHBOARD)
                .then().extract().response();
    }

    public Response getUsers(RequestSpecification spec, int page, String search) {
        String token = TokenManager.getAdminAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .queryParam("page", page)
                .queryParam("search", search)
                .get(ApiEndpoints.ADMIN_USERS)
                .then().extract().response();
    }

    public Response createCoupon(RequestSpecification spec, String code, String description,
                                 String discountType, double discountValue,
                                 double minOrderAmount, int maxUses, String expiresAt) {
        String token = TokenManager.getAdminAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .body("{"
                        + "\"code\":\"" + code + "\","
                        + "\"description\":\"" + description + "\","
                        + "\"discountType\":\"" + discountType + "\","
                        + "\"discountValue\":" + discountValue + ","
                        + "\"minOrderAmount\":" + minOrderAmount + ","
                        + "\"maxUses\":" + maxUses + ","
                        + "\"expiresAt\":\"" + expiresAt + "\""
                        + "}")
                .post(ApiEndpoints.ADMIN_COUPONS)
                .then().extract().response();
    }
}
