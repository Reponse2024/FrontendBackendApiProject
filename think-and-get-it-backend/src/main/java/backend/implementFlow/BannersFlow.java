package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.tokenManager.TokenManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BannersFlow {

    public Response getActiveBanners(RequestSpecification spec) {
        String token = TokenManager.getAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.BANNERS)
                .then().extract().response();
    }
}

