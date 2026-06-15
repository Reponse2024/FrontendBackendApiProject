package backend.tokenManager;

import backend.implementFlow.AuthFlow;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class TokenManager {

    private static String authToken;
    private static String refreshToken;
    private static String adminAuthToken;
    private static String adminRefreshToken;

    public static List<String> generateTokens(RequestSpecification requestSpec) {
        Response response = new AuthFlow().login(requestSpec);
        authToken = response.jsonPath().getString("data.token");
        refreshToken = response.jsonPath().getString("data.refreshToken");
        List<String> tokens = new ArrayList<>();
        tokens.add(authToken);
        tokens.add(refreshToken);
        return tokens;
    }

    public static List<String> generateAdminTokens(RequestSpecification requestSpec) {
        Response response = new AuthFlow().loginAsAdmin(requestSpec);
        adminAuthToken = response.jsonPath().getString("data.token");
        adminRefreshToken = response.jsonPath().getString("data.refreshToken");
        List<String> tokens = new ArrayList<>();
        tokens.add(adminAuthToken);
        tokens.add(adminRefreshToken);
        return tokens;
    }

    public static String getAuthToken(RequestSpecification requestSpec) {
        if (authToken == null) {
            authToken = generateTokens(requestSpec).get(0);
        }
        return authToken;
    }

    public static String getRefreshToken(RequestSpecification requestSpec) {
        if (refreshToken == null) {
            refreshToken = generateTokens(requestSpec).get(1);
        }
        return refreshToken;
    }

    public static String getAdminAuthToken(RequestSpecification requestSpec) {
        if (adminAuthToken == null) {
            adminAuthToken = generateAdminTokens(requestSpec).get(0);
        }
        return adminAuthToken;
    }

    public static String getAdminRefreshToken(RequestSpecification requestSpec) {
        if (adminRefreshToken == null) {
            adminRefreshToken = generateAdminTokens(requestSpec).get(1);
        }
        return adminRefreshToken;
    }
}