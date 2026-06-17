package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.cartConstants.CartTestData;
import backend.tokenManager.TokenManager;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartFlow {

    private final Faker faker = new Faker();

    public String getToken(RequestSpecification requestSpec) {
        return new AuthFlow().login(requestSpec).jsonPath().getString("data.token");
    }

    public Response getCart(RequestSpecification requestSpec) {
        String token= getToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ApiEndpoints.CART)
                .then().log().all()
                .extract().response();
    }

    public Response clearCart(RequestSpecification requestSpec) {
        String token= getToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .delete(ApiEndpoints.CART)
                .then().log().all()
                .extract().response();
    }

    public Response addItemToCart(RequestSpecification requestSpec) {
        String token= getToken(requestSpec);
        String productId = new DynamicProductFlow().getProductId(requestSpec);
        String variantId = new DynamicProductFlow().getVariantId(requestSpec);

        Map<String, Object> payload = new HashMap<>();
        payload.put("productId", productId);
        payload.put("variantId", variantId);
        payload.put("quantity", faker.number().numberBetween(1, 5));

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(ApiEndpoints.CART_ITEMS)
                .then().log().all()
                .extract().response();
    }

    public Response updateItemQuantity(RequestSpecification requestSpec, String itemId, int quantity) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("quantity", quantity);

        return given().spec(requestSpec)
                .pathParam("itemId", itemId)
                .contentType(ContentType.JSON)
                .body(payload)
                .put(ApiEndpoints.CART_ITEM_BY_ID)
                .then().log().all()
                .extract().response();
    }

    public Response removeItemFromCart(RequestSpecification requestSpec, String itemId) {
        return given().spec(requestSpec)
                .pathParam("itemId", itemId)
                .delete(ApiEndpoints.CART_ITEM_BY_ID)
                .then().log().all()
                .extract().response();
    }

    public Response saveItemForLater(RequestSpecification requestSpec, String itemId) {
        return given().spec(requestSpec)
                .pathParam("itemId", itemId)
                .patch(ApiEndpoints.CART_SAVE_FOR_LATER)
                .then().log().all()
                .extract().response();
    }
    public String getCouponCode(RequestSpecification requestSpec) {
        String token= getToken(requestSpec);
        Response response = given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.CART)
                .then().log().all()
                .extract().response();

        return response.jsonPath().getString("data.couponCode");
    }

    public Response applyCoupon(RequestSpecification requestSpec, String code) {
        String token= getToken(requestSpec);
        Map<String, Object> payload = new HashMap<>();
        payload.put("code", code);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(ApiEndpoints.CART_COUPON)
                .then().log().all()
                .extract().response();
    }
    public String getFirstItemId(RequestSpecification requestSpec) {
        String token= getToken(requestSpec);
        Response response = given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.CART)
                .then().log().all()
                .extract().response();
        return response.jsonPath().getString("data.items[0].id");
    }


}
