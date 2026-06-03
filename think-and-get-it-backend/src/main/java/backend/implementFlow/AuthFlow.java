package backend.implementFlow;

import backend.configManager.ConfigManager;
import backend.constants.ApiEndpoints;
import backend.constants.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthFlow {

    public Response login(RequestSpecification requestSpec){
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", UserData.EXISTING_USER_EMAIL);
        payload.put("password", UserData.EXISTING_USER_PASSWORD);

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.LOGIN)
                .then().log().all()
                .extract().response();
    }

    public Response register(RequestSpecification requestSpec) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", UserData.NEW_USER_EMAIL);
        payload.put("password", UserData.NEW_USER_PASSWORD);
        payload.put("firstName", UserData.NEW_USER_FIRST_NAME);
        payload.put("lastName", UserData.NEW_USER_LAST_NAME);

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.REGISTER)
                .then().log().all()
                .extract().response();
    }

    public Response getRefreshedToken(RequestSpecification requestSpec) {
        String refreshToken = login(requestSpec).jsonPath().getString("data.refreshToken");
        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(Map.of("refreshToken", refreshToken))
                .post(ApiEndpoints.REFRESH)
                .then().log().all()
                .extract().response();
    }

    public Response forgotPassword(RequestSpecification requestSpec) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", UserData.EXISTING_USER_EMAIL);

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.FORGOT_PASSWORD)
                .then().log().all()
                .extract().response();
    }

    public Response getCurrentUser(RequestSpecification requestSpec) {
        String token = login(requestSpec).jsonPath().getString("data.token");

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.ME)
                .then().log().all()
                .extract().response();
    }

    public Response verifyEmail(RequestSpecification requestSpec) {
        String verificationToken = register(requestSpec).jsonPath().getString("data.verificationToken");

        return given().spec(requestSpec)
                .get(ApiEndpoints.VERIFY_EMAIL + "/" + verificationToken)
                .then().log().all()
                .extract().response();
    }

    public Response resetPassword(RequestSpecification requestSpec) {
        String resetToken = forgotPassword(requestSpec).jsonPath().getString("data.resetToken");

        Map<String, Object> payload = new HashMap<>();
        payload.put("password", UserData.NEW_USER_PASSWORD);

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.RESET_PASSWORD + "/" + resetToken)
                .then().log().all()
                .extract().response();
    }
}
