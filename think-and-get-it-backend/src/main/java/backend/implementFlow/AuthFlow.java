package backend.implementFlow;
import backend.configManager.ConfigManager;
import backend.constants.ApiEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AuthFlow {
    private RequestSpecification requestSpec;

    public Response login(RequestSpecification requestSpec){

        Map<String, Object> payload = new HashMap<>();

        payload.put("email", ConfigManager.get("email"));
        payload.put("password", ConfigManager.get("password"));


       return given().spec(requestSpec)
                .body(payload)
                .when()
                .post(ApiEndpoints.LOGIN)
                .then().log().all()
                .extract().response();
    }
    public Response register(RequestSpecification requestSpec) {
        Map<String, Object> payload = new HashMap<>();

        payload.put("email", "newuser@thinkandgetit.com");
        payload.put("password", "Password123!");
        payload.put("firstName", "Diemme");
        payload.put("lastName", "Merci");

       return given().spec(requestSpec)
               .body(payload)
               .when().post(ApiEndpoints.REGISTER)
               .then().log().all()
               .extract().response();
    }
    public Response getRefreshedToken(RequestSpecification requestSpec) {
        String refreshToken = login(requestSpec).jsonPath().getString("data.refreshToken");
        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(Map.of("refreshToken", refreshToken))
                .post(ApiEndpoints.REFRESH)
                .then().log().all().extract().response();
    }
    public Response verifyEmail(RequestSpecification requestSpec) {
        String verificationToken = getRefreshedToken(requestSpec).jsonPath().getString("data.token");
        return given().spec(requestSpec)
                .when()
                .get(ApiEndpoints.VERIFY_EMAIL + "/" + verificationToken)
                .then().log().all()
                .extract().response();
    }
    public Response forgotPassword(RequestSpecification requestSpec) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", ConfigManager.get("email"));

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(ApiEndpoints.FORGOT_PASSWORD)
                .then().log().all()
                .extract().response();
    }
    public Response resetPassword(RequestSpecification requestSpec) {
        String resetToken = getRefreshedToken(requestSpec).jsonPath().getString("data.token");
        Map<String, Object> payload = new HashMap<>();
        payload.put("password", "NewPassword1234!");

        return given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ApiEndpoints.RESET_PASSWORD + "/" + resetToken)
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
    }



