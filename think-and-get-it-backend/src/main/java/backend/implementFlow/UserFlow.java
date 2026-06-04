package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.userConstants.UsersData;
import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserFlow {

    public Response updateProfile(RequestSpecification requestSpec) {
        Map<String, Object> payload = Map.of(
                "firstName", UsersData.PROFILE_FIRST_NAME,
                "lastName", UsersData.PROFILE_LAST_NAME,
                "phone", UsersData.PROFILE_PHONE
        );

        return given().spec(requestSpec)
                .header("Authorization", "Bearer "+ TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(ApiEndpoints.PROFILE)
                .then().log().all()
                .extract().response();
    }

    public Response uploadAvatar(RequestSpecification requestSpec) {
        File avatarFile = new File("src/test/resources/avatar.jpg");

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .multiPart("avatar", avatarFile)
                .when()
                .post(ApiEndpoints.AVATAR)
                .then().log().all()
                .extract().response();
    }

    public Response changePassword(RequestSpecification requestSpec, String currentPassword, String newPassword) {
        Map<String, Object> payload = Map.of(
                "currentPassword", currentPassword,
                "newPassword", newPassword
        );

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(ApiEndpoints.CHANGE_PASSWORD)
                .then().log().all()
                .extract().response();
    }

    public Response getAddresses(RequestSpecification requestSpec) {
        AuthFlow authFlow = new AuthFlow();
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + authFlow.login(requestSpec))
                .when()
                .get(ApiEndpoints.ADDRESSES)
                .then().log().all()
                .extract().response();
    }

    public Response addAddress(RequestSpecification requestSpec) {
        Map<String, Object> payload = Map.of(
                "label", UsersData.ADDRESS_LABEL,
                "firstName", UsersData.PROFILE_FIRST_NAME,
                "lastName", UsersData.PROFILE_LAST_NAME,
                "phone", UsersData.PROFILE_PHONE,
                "street", UsersData.ADDRESS_STREET,
                "city", UsersData.ADDRESS_CITY,
                "state", UsersData.ADDRESS_STATE,
                "country", UsersData.ADDRESS_COUNTRY,
                "postalCode", UsersData.ADDRESS_POSTAL_CODE,
                "isDefault", true
        );

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(ApiEndpoints.ADDRESSES)
                .then().log().all()
                .extract().response();
    }
}
