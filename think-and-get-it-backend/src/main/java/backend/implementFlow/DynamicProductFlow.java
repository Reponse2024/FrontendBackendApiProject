package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

import static io.restassured.RestAssured.given;

public class DynamicProductFlow {

    private Response createProduct(RequestSpecification requestSpec) {
        String token = TokenManager.getAdminAuthToken(requestSpec);

        Map<String, Object> variant = new HashMap<>();
        variant.put("size", "M");
        variant.put("color", "Blue");
        variant.put("colorHex", "#0000FF");
        variant.put("sku", "SKU-" + System.currentTimeMillis());
        variant.put("stock", 10);
        variant.put("price", 1000);

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Test Product " + System.currentTimeMillis());
        payload.put("description", "Auto-generated product for testing");
        payload.put("price", 1200);
        payload.put("comparePrice", 1500);
        payload.put("categoryId", "12dfe208-7fef-4985-b377-8837026e1919");
        payload.put("tags", Arrays.asList("automation", "test"));
        payload.put("isFeatured", true);
        payload.put("isFlashSale", true);
        payload.put("flashSalePrice", 900);
        payload.put("variants", Collections.singletonList(variant));

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.PRODUCTS)
                .then().extract().response();
    }

    public String getProductId(RequestSpecification requestSpec) {
        Response response = createProduct(requestSpec);
        String productId = response.jsonPath().getString("data.id");

        if (productId == null) {
            throw new IllegalStateException("Product ID is null — product creation failed.");
        }
        return productId;
    }

    public String getVariantId(RequestSpecification requestSpec) {
        Response response = createProduct(requestSpec);
        String variantId = response.jsonPath().getString("data.variants[0].id");

        if (variantId == null) {
            throw new IllegalStateException("Variant ID is null — product creation failed.");
        }
        return variantId;
    }
}
