package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.categoriesConstants.CategoryData;
import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CategoryFlow {

    public Response getAllCategories(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .get(ApiEndpoints.CATEGORIES)
                .then().log().all()
                .extract().response();
    }

    // Admin only: Create category
    public Response createCategory(RequestSpecification requestSpec) {
        Map<String, Object> payload = Map.of(
                "name", CategoryData.NAME,
                "description", CategoryData.DESCRIPTION,
                "parentId", CategoryData.PARENT_ID
        );

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAdminAuthToken(requestSpec))
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.CATEGORIES)
                .then().log().all()
                .extract().response();
    }

    public Response getCategoryBySlug(RequestSpecification requestSpec) {
        return given().spec(requestSpec)
                .pathParam("slug", CategoryData.SLUG)
                .get(ApiEndpoints.CATEGORY_BY_SLUG)
                .then().log().all()
                .extract().response();
    }
}
