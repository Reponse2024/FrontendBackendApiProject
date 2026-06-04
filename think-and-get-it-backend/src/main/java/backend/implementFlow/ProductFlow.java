package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.productsConstants.ProductData;
import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductFlow {

    public Response getAllProducts(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .queryParam("page", 1)
                .queryParam("limit", 20)
                .get(ApiEndpoints.PRODUCTS)
                .then().log().all()
                .extract().response();
    }

    public Response createProduct(RequestSpecification requestSpec) {
        Map<String, Object> payload = Map.of(
                "name", ProductData.NAME,
                "description", ProductData.DESCRIPTION,
                "price", ProductData.PRICE,
                "comparePrice", ProductData.COMPARE_PRICE,
                "categoryId", ProductData.CATEGORY_ID,
                "tags", ProductData.TAGS,
                "isFeatured", ProductData.IS_FEATURED,
                "isFlashSale", ProductData.IS_FLASH_SALE,
                "flashSalePrice", ProductData.FLASH_SALE_PRICE,
                "variants", ProductData.VARIANTS
        );

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.PRODUCTS)
                .then().log().all()
                .extract().response();
    }

    public Response getProductBySlug(RequestSpecification requestSpec, String validSlug) {
        return given().spec(requestSpec)
                .pathParam("slug", ProductData.SLUG)
                .get(ApiEndpoints.PRODUCT_BY_SLUG)
                .then().log().all()
                .extract().response();
    }

    public Response updateProduct(RequestSpecification requestSpec) {
        Map<String, Object> payload = Map.of("price", 949.99);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .pathParam("id", ProductData.ID)
                .body(payload)
                .put(ApiEndpoints.PRODUCT_BY_ID)
                .then().log().all()
                .extract().response();
    }

    public Response deleteProduct(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .pathParam("id", ProductData.ID)
                .delete(ApiEndpoints.PRODUCT_BY_ID)
                .then().log().all()
                .extract().response();
    }

    public Response uploadProductImages(RequestSpecification requestSpec) {
        File image = new File("src/test/resources/product.jpg");

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .multiPart("images", image)
                .pathParam("id", ProductData.ID)
                .post(ApiEndpoints.PRODUCT_IMAGES)
                .then().log().all()
                .extract().response();
    }

    public Response getTrendingProducts(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .get(ApiEndpoints.PRODUCT_TRENDING)
                .then().log().all()
                .extract().response();
    }

    public Response getFlashSaleProducts(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .get(ApiEndpoints.PRODUCT_FLASH_SALES)
                .then().log().all()
                .extract().response();
    }

    public Response getRelatedProducts(RequestSpecification requestSpec, String validIdSmartphone) {
        return given().spec(requestSpec)
                .pathParam("id", ProductData.ID)
                .get(ApiEndpoints.PRODUCT_RELATED)
                .then().log().all()
                .extract().response();
    }
}
