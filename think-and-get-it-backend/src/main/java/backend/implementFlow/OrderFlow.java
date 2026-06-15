package backend.implementFlow;

import backend.constants.OrderConstants.OrderStatusConstants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import backend.constants.ApiEndpoints;
import backend.tokenManager.TokenManager;

public class OrderFlow {

    public String ensureOrderExists(RequestSpecification requestSpec) {
        Response orderResponse = placeOrder(requestSpec);
        return orderResponse.jsonPath().getString("data.id");
    }

    public Response placeOrder(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);
        new CartFlow().addItemToCart(requestSpec);
        new UserFlow().addAddress(requestSpec);
        Response addressResponse = new UserFlow().getAddresses(requestSpec);
        String addressId = addressResponse.jsonPath().getString("data[0].id");
        Map<String, Object> payload = new HashMap<>();
        payload.put("addressId", addressId);
        payload.put("paymentMethod", "CASH_ON_DELIVERY");
        payload.put("notes", "Deliver before noon");
        payload.put("shippingFee", 0);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.ORDERS)
                .then().extract().response();
    }

    public Response placeOrderWithoutCart(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);
        new UserFlow().addAddress(requestSpec);
        Response addressResponse = new UserFlow().getAddresses(requestSpec);
        String addressId = addressResponse.jsonPath().getString("data[0].id");
        Map<String, Object> payload = new HashMap<>();
        payload.put("addressId", addressId);
        payload.put("paymentMethod", "CASH_ON_DELIVERY");
        payload.put("notes", "Deliver before noon");
        payload.put("shippingFee", 0);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.ORDERS)
                .then().extract().response();
    }

    public Response getUserOrders(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.ORDERS)
                .then().extract().response();
    }

    public Response getUserOrdersWithoutAuth(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .get(ApiEndpoints.ORDERS)
                .then().extract().response();
    }

    public Response getSingleOrder(RequestSpecification requestSpec) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .get(ApiEndpoints.ORDER_BY_ID)
                .then().extract().response();
    }

    public Response getSingleOrderWithInvalidId(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "invalid-id")
                .get(ApiEndpoints.ORDER_BY_ID)
                .then().extract().response();
    }

    public Response cancelOrder(RequestSpecification requestSpec) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .patch(ApiEndpoints.ORDER_CANCEL)
                .then().extract().response();
    }

    public Response returnOrder(RequestSpecification requestSpec) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAuthToken(requestSpec);
        Map<String, Object> payload = new HashMap<>();
        payload.put("reason", "Received damaged item");
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .contentType(ContentType.JSON)
                .body(payload)
                .patch(ApiEndpoints.ORDER_RETURN)
                .then().extract().response();
    }

    public Response returnOrderWithoutReason(RequestSpecification requestSpec) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .patch(ApiEndpoints.ORDER_RETURN)
                .then().extract().response();
    }

    public Response uploadPaymentProof(RequestSpecification requestSpec) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAuthToken(requestSpec);
        File proof = new File("src/test/resources/payment-proof.png");
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .multiPart("proof", proof)
                .post(ApiEndpoints.ORDER_PAYMENT_PROOF)
                .then().extract().response();
    }

    public Response uploadPaymentProofWithoutFile(RequestSpecification requestSpec) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .post(ApiEndpoints.ORDER_PAYMENT_PROOF)
                .then().extract().response();
    }

    public Response getAllOrdersAdmin(RequestSpecification requestSpec) {
        String token = TokenManager.getAdminAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.ORDER_ADMIN_ALL)
                .then().extract().response();
    }

    public Response getAllOrdersAdminWithoutRole(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.ORDER_ADMIN_ALL)
                .then().extract().response();
    }

    public Response updateOrderStatusAdmin(RequestSpecification requestSpec, String status, String message, String trackingNumber) {
        String orderId = ensureOrderExists(requestSpec);
        String token = TokenManager.getAdminAuthToken(requestSpec);
        Map<String, Object> payload = new HashMap<>();
        payload.put("status", status);
        payload.put("message", message);
        payload.put("trackingNumber", trackingNumber);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", orderId)
                .contentType(ContentType.JSON)
                .body(payload)
                .patch(ApiEndpoints.ORDER_ADMIN_STATUS)
                .then().extract().response();
    }

    public Response updateOrderStatusAdminWithInvalidStatus(RequestSpecification requestSpec) {
        return updateOrderStatusAdmin(requestSpec, OrderStatusConstants.INVALID_STATUS, "Trying invalid status", OrderStatusConstants.TRACKING_NUMBER_INVALID);
    }

    public Response updateOrderStatusAdminWithoutAuth(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .pathParam("id", "some-order-id")
                .contentType(ContentType.JSON)
                .body("{\"status\":\"DELIVERED\",\"message\":\"Delivered\",\"trackingNumber\":\"TRACK123\"}")
                .patch(ApiEndpoints.ORDER_ADMIN_STATUS)
                .then().extract().response();
    }
}
