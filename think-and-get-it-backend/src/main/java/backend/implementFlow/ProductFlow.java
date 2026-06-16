package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.productsConstants.ProductData;
import backend.tokenManager.TokenManager;
import backend.utils.RequestHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

public class ProductFlow {

    public Response getAllProducts(RequestSpecification spec) {

        return RequestHelper.get(spec, ApiEndpoints.PRODUCTS);
    }

    public Response createProduct(RequestSpecification spec) {
        Map<String, Object> payload = Map.of("name", "Smartphone X", "price", 999.99);
        return RequestHelper.postWithAuth(spec, ApiEndpoints.PRODUCTS, payload);
    }

    public Response getProductBySlug(RequestSpecification spec, String slug) {
        return RequestHelper.getWithPath(spec, ApiEndpoints.PRODUCT_BY_SLUG, "slug", slug);
    }

    public Response updateProduct(RequestSpecification spec) {
        Map<String, Object> payload = Map.of("price", 949.99);
        return RequestHelper.putWithAuth(spec, ApiEndpoints.PRODUCT_BY_ID, "id", ProductData.VALID_ID_SMARTPHONE, payload);
    }

    public Response deleteProduct(RequestSpecification spec) {
        return RequestHelper.deleteWithAuth(spec, ApiEndpoints.PRODUCT_BY_ID, "id", ProductData.VALID_ID_SMARTPHONE);
    }

    public Response uploadProductImages(RequestSpecification spec) {
        File image = new File("src/test/resources/product.jpg");
        return RequestHelper.uploadImage(spec, ApiEndpoints.PRODUCT_IMAGES, "id", ProductData.VALID_ID_SMARTPHONE, image);
    }

    public Response getTrendingProducts(RequestSpecification spec) {
        return RequestHelper.get(spec, ApiEndpoints.PRODUCT_TRENDING);
    }

    public Response getFlashSaleProducts(RequestSpecification spec) {
        return RequestHelper.get(spec, ApiEndpoints.PRODUCT_FLASH_SALES);
    }

    public Response getRelatedProducts(RequestSpecification spec, String id) {
        return RequestHelper.getWithPath(spec, ApiEndpoints.PRODUCT_RELATED, "id", id);
    }
}
