package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.tokenManager.TokenManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SearchFlow {

    public Response search(RequestSpecification spec, String query, Integer page, Integer limit,
                           String category, Double minPrice, Double maxPrice, String sort) {
        String token = TokenManager.getAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .queryParam("q", query)
                .queryParam("page", page)
                .queryParam("limit", limit)
                .queryParam("category", category)
                .queryParam("minPrice", minPrice)
                .queryParam("maxPrice", maxPrice)
                .queryParam("sort", sort)
                .get(ApiEndpoints.SEARCH)
                .then().extract().response();
    }

    public Response suggestions(RequestSpecification spec, String query) {
        String token = TokenManager.getAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .queryParam("q", query)
                .get(ApiEndpoints.SEARCH_SUGGESTIONS)
                .then().extract().response();
    }

    public Response trending(RequestSpecification spec) {
        String token = TokenManager.getAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.SEARCH_TRENDING)
                .then().extract().response();
    }
}
