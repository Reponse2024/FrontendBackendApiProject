package tokenManager;

import backend.constants.ApiEndpoints;
import backend.constants.HttpStatus;
import backend.configManager.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String authToken;
    private static String refreshToken;

    public static void generateTokens() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(
                        new java.util.HashMap<String, Object>() {{
                            put("email", ConfigManager.get("email"));
                            put("password", ConfigManager.get("password"));
                        }}
                )
                .post(ApiEndpoints.LOGIN)
                .then()
                .statusCode(HttpStatus.OK.code())
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
        refreshToken = response.jsonPath().getString("data.refreshToken");
    }

    public static String getAuthToken() {
        if (authToken == null) {
            generateTokens();
        }
        return authToken;
    }

    public static String getRefreshToken() {
        if (refreshToken == null) {
            generateTokens();
        }
        return refreshToken;
    }

    public static void refreshAuthToken() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(
                        new java.util.HashMap<String, Object>() {{
                            put("refreshToken", getRefreshToken());
                        }}
                )
                .post(ApiEndpoints.REFRESH)
                .then()
                .statusCode(HttpStatus.OK.code())
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
    }
}
