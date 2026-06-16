package backend.implementFlow;

import backend.configManager.ConfigManager;
import backend.constants.ApiEndpoints;
import backend.constants.AuthData;
import backend.tokenManager.TokenManager;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthFlow {

    private final Faker faker = new Faker();

    public Response login(RequestSpecification requestSpec){
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", ConfigManager.get("email"));
        payload.put("password", ConfigManager.get("password"));

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.LOGIN)
                .then().log().all()
                .extract().response();
    }
    public Response loginAsAdmin(RequestSpecification requestSpec) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", ConfigManager.get("adminEmail"));
        payload.put("password", ConfigManager.get("adminPassword"));

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.LOGIN)
                .then().log().all()
                .extract().response();
    }

    public Response register(RequestSpecification requestSpec) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", faker.internet().emailAddress());
        payload.put("password", faker.internet().password(8, 16));
        payload.put("firstName", faker.name().firstName());
        payload.put("lastName", faker.name().lastName());

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
        payload.put("email", ConfigManager.get("email"));

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
        String verificationToken = register(requestSpec).jsonPath().getString("data.token");

        return given().spec(requestSpec)
                .pathParam("token", verificationToken)
                .header("Authorization", "Bearer " + verificationToken)
                .get(ApiEndpoints.VERIFY_EMAIL)
                .then().log().all()
                .extract().response();
    }
    public Response verifyEmailWithInvalidToken(RequestSpecification requestSpec,  String token) {
        return given().spec(requestSpec)
                .pathParam("token", token)
                .get(ApiEndpoints.VERIFY_EMAIL)
                .then().log().all()
                .extract().response();
    }

    public Response resetPassword(RequestSpecification requestSpec) {

        String resetToken = login(requestSpec).jsonPath().getString("data.token");

        Map<String, Object> payload = new HashMap<>();
        payload.put("currentPassword", ConfigManager.get("password"));
        payload.put("newPassword", faker.internet().password(8, 16));

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .pathParam("token", resetToken)
                .body(payload)
                .post(ApiEndpoints.RESET_PASSWORD)
                .then().log().all()
                .extract().response();
    }
}
