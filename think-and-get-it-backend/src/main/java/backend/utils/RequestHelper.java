package backend.utils;

import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RequestHelper {

    public static Response get(RequestSpecification spec, String endpoint) {
        return given().spec(spec)
                .get(endpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response getWithPath(RequestSpecification spec, String endpoint, String key, String value) {
        return given().spec(spec)
                .pathParam(key, value)
                .get(endpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response postWithAuth(RequestSpecification spec, String endpoint, Object body) {
        return given().spec(spec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .body(body)
                .post(endpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response putWithAuth(RequestSpecification spec, String endpoint, String key, String value, Object body) {
        return given().spec(spec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .contentType(ContentType.JSON)
                .pathParam(key, value)
                .body(body)
                .put(endpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response deleteWithAuth(RequestSpecification spec, String endpoint, String key, String value) {
        return given().spec(spec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .pathParam(key, value)
                .delete(endpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response uploadImage(RequestSpecification spec, String endpoint, String key, String value, File image) {
        return given().spec(spec)
                .header("Authorization", "Bearer " + TokenManager.getAuthToken())
                .multiPart("images", image)
                .pathParam(key, value)
                .post(endpoint)
                .then().log().all()
                .extract().response();
    }
}
