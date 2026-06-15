package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.reviewsConstants.ReviewPayload;
import backend.constants.reviewsConstants.ReviewsTestData;
import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ReviewFlow {

    public Response getReviews(RequestSpecification requestSpec, int page, String sort) {
        String token = TokenManager.getAuthToken(requestSpec);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", ReviewsTestData.DEFAULT_PRODUCT_ID)
                .queryParam("page", page)
                .queryParam("sort", sort)
                .get(ApiEndpoints.REVIEWS)
                .then().extract().response();
    }

    public Response submitReview(RequestSpecification requestSpec, ReviewPayload review) {
        String token = TokenManager.getAuthToken(requestSpec);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", ReviewsTestData.DEFAULT_PRODUCT_ID)
                .contentType(ContentType.JSON)
                .body(review)
                .post(ApiEndpoints.REVIEWS)
                .then().extract().response();
    }
}
